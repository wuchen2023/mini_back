package com.web.back.controller.admin;

import com.alibaba.druid.support.logging.Log;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.*;
import com.web.back.mapper.BlindBoxMapper;
import com.web.back.service.*;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.admin.exam.ExamPaperTitleItemVM;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassResponseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
@Api("盲盒的Api")
@RestController
@RequestMapping(value = "/api/admin/blindbox")
public class BlindboxController {
    /**
     * 随机抽取一道题目并返回
     */
    private final QuestionService questionService;

    private final TextContentService textContentService;

    private StudentService studentService;

    private TeacherService teacherService;
    private final StudentClassService studentClassService;

    private final ExamPaperService examPaperService;

    private final BlindBoxService blindBoxService;

    private final BlindBoxMapper blindBoxMapper;
    @Autowired
    public BlindboxController(QuestionService questionService, TextContentService textContentService, StudentService studentService, StudentClassService studentClassService, ExamPaperService examPaperService, TeacherService teacherService, BlindBoxService blindBoxService, BlindBoxMapper blindBoxMapper) {
        this.questionService = questionService;
        this.textContentService = textContentService;
        this.studentService = studentService;
        this.studentClassService = studentClassService;
        this.examPaperService = examPaperService;
        this.teacherService = teacherService;
        this.blindBoxService = blindBoxService;
        this.blindBoxMapper = blindBoxMapper;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 随机抽取一位学生
     */
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

//    @ResponseBody
//    @ApiOperation("依据老师课程名称查询到当前课程中学生课程中所有学生的id,前端调用此接口获取到当前课程下的所有学生id，在前端处理随机选取一名学生的id")
//    @PostMapping("course_get_student")
//    public RestResponse<PageInfo<StudentClassResponseVM>> pageList(@RequestBody StudentClassPageRequestVM model){
//        PageInfo<StudentClass> pageInfo = studentClassService.page(model);
//        PageInfo<StudentClassResponseVM> page = PageInfoHelper.copyMap(pageInfo,q->{
//            StudentClassResponseVM vm = modelMapper.map(q, StudentClassResponseVM.class);
//            vm.setStudent_id(q.getStudentId());
//            return vm;
//        });
//        return RestResponse.ok(page);
//    }

    @ResponseBody
    @ApiOperation("抽取一位学生返回学生的姓名和班级名称")
    @PostMapping("get_student")
    public RestResponse<StudentClassResponseVM> get_student(@RequestParam String className) {
        /**
         * 传入当前班级的名称，查询出所在班级中所有的学生的id，然后再随机抽一个id，再在学生表中获取学生的信息。
         */
        StudentClass studentClass = studentClassService.get_student(className);
        Integer student_id = studentClass.getStudentId();
        String name = studentService.student_id_get_name(student_id);
        StudentClassResponseVM studentClassResponseVM = new StudentClassResponseVM();
        studentClassResponseVM.setName(name);
        studentClassResponseVM.setClass_name(className);

        return RestResponse.ok(studentClassResponseVM);
    }


    /**
     * 传入老师标识码和账户验证老师登录，然后调用盲盒接口开始抽题，
     */
    @ResponseBody
    @ApiOperation("随机抽一道题")
    @PostMapping("blindbox")
    public RestResponse<ExamPaperEditRequestVM> blindbox(@RequestParam String coursename, @RequestParam String stuaccount, @RequestParam String teacher_account) {
        System.out.println("查询的结果是：" + questionService.selectAllCount());
        if (questionService.selectAllCount() > 0) {
            List<Integer> questionNumbers = questionService.findAllQuestionIds();
            System.out.println("题库中已有的题目号为：" + questionNumbers);
            Integer randomQuestionNumber = getRandomQuestionNumber(questionNumbers);
            System.out.println("随机选中的题号是：" + randomQuestionNumber);
            QuestionEditRequestVM questionVM = questionService.getQuestionEditRequestVM(randomQuestionNumber);
            //下面把抽到的一道题目设置为一套试卷
            ExamPaperEditRequestVM examPaperEditRequestVM = new ExamPaperEditRequestVM();
            examPaperEditRequestVM.setSubjectId(1); //这里学科该怎么设置成不同的呢？或者是对应的学科
            examPaperEditRequestVM.setPaperType(1);
            examPaperEditRequestVM.setCourseName(coursename);
            examPaperEditRequestVM.setStuAccount(stuaccount);
            //还没有校验学生在不在班级，班级有没有存在
            examPaperEditRequestVM.setName(createNewName(coursename, stuaccount));
            examPaperEditRequestVM.setSuggestTime(2);

            List<ExamPaperTitleItemVM> titleItems = new ArrayList<>();
            ExamPaperTitleItemVM item1 = new ExamPaperTitleItemVM();
            item1.setName("题目");
            List<QuestionEditRequestVM> questionList = new ArrayList<>();
            questionList.add(questionVM);
            item1.setQuestionItems(questionList);
            titleItems.add(item1);
            examPaperEditRequestVM.setTitleItems(titleItems);
            Student student = studentService.get_detail_by_account(stuaccount);
            ExamPaper examPaper = examPaperService.savePaperFromVM_stu(examPaperEditRequestVM, student);
            ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
            // 在blind_box中插入相关数据
            BlindBox blindBox = new BlindBox(coursename,stuaccount, examPaper.getId(), teacher_account);
            blindBoxMapper.insert(blindBox);
            return RestResponse.ok(newVM);
        }
        return null;
    }
    public  String createNewName(String coursename, String stuaccount) {
        // 生成随机三位数
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;
        //新增一个根据课程id，查询到课程名字
//        String courseName = teacherService.get_courseName_by_id(infoclasscontentid);

        // 合并字符串
        String mergedString = "班级" + coursename + "_" + "学生" + stuaccount + "_" + randomNumber;

        System.out.println("合并后的字符串：" + mergedString);
        return mergedString;
    }


    public static Integer getRandomQuestionNumber(List<Integer> questionNumbers) {
        Random random = new Random();
        int randomIndex = random.nextInt(questionNumbers.size()); // 生成随机索引
        return questionNumbers.get(randomIndex); // 返回随机选中的题号
    }

    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-teacher", value, 7, TimeUnit.DAYS);
    }

    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-teacher");
    }

    /**
     * 抽题完成后在前端做题，做完题目后要记录做题的记录
     */
    /**
     * 我应该怎么考虑将做题的记录存放进数据库中
     */

    @ResponseBody
    @ApiOperation("盲盒老师或者学生查看盲盒记录，只能返回抽到过哪些学生以及回答正确的情况，老师端就只需要输入课程名和老师账户，学生就输入课程名和学生账户")
    @PostMapping("blindbox_view_history")
    public List<BlindBox>  blindbox_view_history(@RequestParam(required = true) String class_name, @RequestParam(required = false) String stu_account, @RequestParam(required = false) String teacher_account){
            return blindBoxService.blindbox_view_history(class_name, stu_account, teacher_account);
    }


    @ResponseBody
    @ApiOperation("盲盒某一条记录删除，点击某一条记录，输入该条记录的id")
    @PostMapping("blindbox_delete")
    public ResposeResult blindbox_delete(@RequestParam Integer id){

        try{
            blindBoxMapper.deleteById(id);
            return new ResposeResult(1, "删除成功");
        }catch (Exception e){
            return new ResposeResult(0, "删除失败");
        }
    }



}
