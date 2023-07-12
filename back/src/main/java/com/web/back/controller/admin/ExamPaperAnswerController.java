package com.web.back.controller.admin;

import com.web.back.domain.*;
import com.web.back.domain.enums.QuestionTypeEnum;
import com.web.back.service.*;
import com.web.back.state.RestResponse;
import com.web.back.utils.ExamUtil;
import com.web.back.viewmodel.student.exam.ExamPaperSubmitItemVM;
import com.web.back.viewmodel.student.exam.ExamPaperSubmitVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/7/12.
 * @DESC:
 */


@Api("试卷做完进行提交的APi")
@RestController
@RequestMapping(value = "/api/admin/student/exampaper/answer")
public class ExamPaperAnswerController {

    private final ExamPaperAnswerService examPaperAnswerService;

    private final SubjectService subjectService;

    private final ApplicationEventPublisher eventPublisher;

    private final ExamPaperService examPaperService;

    private final TeacherService teacherService;

    private final StudentService studentService;
    @Autowired
    public ExamPaperAnswerController(ExamPaperAnswerService examPaperAnswerService, SubjectService subjectService, ApplicationEventPublisher eventPublisher, ExamPaperService examPaperService, TeacherService teacherService, StudentService studentService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.subjectService = subjectService;
        this.eventPublisher = eventPublisher;
        this.examPaperService = examPaperService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    /**
     * 试卷提交的接口
     */
    @PostMapping("/answerSubmit")
    @ApiOperation("小程序端-试卷答题提交")
    public RestResponse answerSubmit(HttpServletRequest request) {
        ExamPaperSubmitVM examPaperSubmitVM = requestToExamPaperSubmitVM(request);
        // 要传入用户对象，用于记录谁答的这套试卷
        // 前端request中还要新增storage中的当前登录用户的account，然后一句account获取到用户信息
        String people = request.getParameter("people");
        String account = request.getParameter("account");
        Integer uid = 0;
        if(people.equals("老师")){
            //是老师，就调用查询老师的方法
            Teacher teacher = teacherService.get_detail_by_account(account);
            uid = teacher.getId();
            System.out.println("传入老师的账号："+people+","+"获得老师的id："+uid);
        }else if(people.equals("学生")){
            //是学生，调用学生的方法
            Student student = studentService.get_detail_by_account(account);
            uid = student.getId();
            System.out.println("传入学生的账号："+people+","+"获得学生的id："+uid);
        }
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswer(examPaperSubmitVM, uid);
        if (null == examPaperAnswerInfo) {
            return RestResponse.fail(2, "试卷不能重复做");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = ExamUtil.scoreToVM(userScore);
//        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
//        String content = user.getUserName() + " 提交试卷：" + examPaperAnswerInfo.getExamPaper().getName()
//                + " 得分：" + scoreVm
//                + " 耗时：" + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
//        userEventLog.setContent(content);
//        eventPublisher.publishEvent(new CalculateExamPaperAnswerCompleteEvent(examPaperAnswerInfo));
//        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(scoreVm);
    }


    private ExamPaperSubmitVM requestToExamPaperSubmitVM(HttpServletRequest request) {
        ExamPaperSubmitVM examPaperSubmitVM = new ExamPaperSubmitVM(); //创建一个examPaperSubmitVM，作为返回结果

        examPaperSubmitVM.setId(Integer.parseInt(request.getParameter("id")));
        examPaperSubmitVM.setDoTime(Integer.parseInt(request.getParameter("doTime")));
        //使用Collections.list方法获取所有请求参数的名称，并通过流操作进行筛选。只选择包含"_"字符的参数名称。将筛选后的结果收集到parameterNames列表中。
        List<String> parameterNames = Collections.list(request.getParameterNames()).stream()
                .filter(n -> n.contains("_"))
                .collect(Collectors.toList());
        //题目答案按序号分组
        Map<String, List<String>> questionGroup = parameterNames.stream().collect(Collectors.groupingBy(p -> p.substring(0, p.indexOf("_"))));
        //创建一个空的answerItems列表，用于存储每个题目的提交项。
        List<ExamPaperSubmitItemVM> answerItems = new ArrayList<>();
        questionGroup.forEach((k, v) -> {
            ExamPaperSubmitItemVM examPaperSubmitItemVM = new ExamPaperSubmitItemVM();
            String p = v.get(0);
            String[] keys = p.split("_");
            //对于每个题目，创建一个ExamPaperSubmitItemVM对象。
            //从题目对应的参数列表中获取第一个参数名称，并通过"_"进行分割。将分割后的结果存储在keys数组中。keys[0]表示题目中的项序号，keys[1]表示题目的ID，keys[2]表示题目的类型
            examPaperSubmitItemVM.setQuestionId(Integer.parseInt(keys[1]));
            examPaperSubmitItemVM.setItemOrder(Integer.parseInt(keys[0]));
            QuestionTypeEnum typeEnum = QuestionTypeEnum.fromCode(Integer.parseInt(keys[2]));
            //如果题目的参数列表长度为1，表示是单选或多选题的答案。从request对象中获取参数值，并将它设置为examPaperSubmitItemVM对象的内容。
            if (v.size() == 1) {
                String content = request.getParameter(p);
                examPaperSubmitItemVM.setContent(content);
                if (typeEnum == QuestionTypeEnum.MultipleChoice) {
                    examPaperSubmitItemVM.setContentArray(Arrays.asList(content.split(",")));
                }
            } else {  //多个空 填空题
                //如果题目的参数列表长度大于1，表示是填空题的答案。将参数列表按照最后一个数字进行排序，并通过映射转换为参数值，存储在answers列表中。将answers列表设置为examPaperSubmitItemVM对象的内容数组。
                List<String> answers = v.stream().sorted(Comparator.comparingInt(ExamUtil::lastNum)).map(inputKey -> request.getParameter(inputKey)).collect(Collectors.toList());
                examPaperSubmitItemVM.setContentArray(answers);
            }
            answerItems.add(examPaperSubmitItemVM); //将examPaperSubmitItemVM对象添加到answerItems列表中。
        });
        examPaperSubmitVM.setAnswerItems(answerItems); //将answerItems列表设置为examPaperSubmitVM对象的答案项
        return examPaperSubmitVM;
    }

}
