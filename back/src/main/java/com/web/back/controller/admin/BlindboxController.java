package com.web.back.controller.admin;

import com.alibaba.druid.support.logging.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.*;
import com.web.back.mapper.BlindBoxMapper;
import com.web.back.service.*;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.DateTimeUtil;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.blindbox.PageInfoBlindBoxRequestVM;
import com.web.back.viewmodel.admin.blindbox.ViewHistoryVM;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.admin.exam.ExamPaperTitleItemVM;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassResponseVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageResponseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    public RestResponse<StudentClassResponseVM> get_student(@RequestParam String className, @RequestParam String teacherAccount) {
        /**
         * 传入当前班级的名称，查询出所在班级中所有的学生的id，然后再随机抽一个id，再在学生表中获取学生的信息。
         */
        StudentClass studentClass = studentClassService.get_student(className);
        Integer student_id = studentClass.getStudentId();
        Student student = studentService.student_id_get_name(student_id);
        StudentClassResponseVM studentClassResponseVM = new StudentClassResponseVM();
        studentClassResponseVM.setStu_name(student.getName());
        studentClassResponseVM.setStu_account(student.getAccount());
        studentClassResponseVM.setClass_name(className);
        //抽完学生后就插入blind_box表中
        BlindBox blindBox = new BlindBox(student.getAccount(),teacherAccount, className);
        studentClassResponseVM.setCreate_time(blindBox.getCreate_time());
        blindBoxMapper.insert(blindBox);
        return RestResponse.ok(studentClassResponseVM);
    }


    /**
     * 传入老师标识码和账户验证老师登录，然后调用盲盒接口开始抽题，
     */
    @ResponseBody
    @ApiOperation("随机抽一道题")
    @PostMapping("blindbox")
//    public RestResponse<ExamPaperEditRequestVM> blindbox(@RequestParam Integer blindBoxId,@RequestParam String className, @RequestParam String stuAccount, @RequestParam String teacherAccount) {
    public RestResponse<ExamPaperEditRequestVM> blindbox(@RequestParam Integer blindBoxId) {
        System.out.println("查询的结果是：" + questionService.selectAllCount());
        //根据blindboxid查询到该条记录，并获取数据
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",blindBoxId);
//        BlindBox blindBox = blindBoxMapper.selectOne(queryWrapper);
        BlindBox blindBox = blindBoxMapper.findInfoById(blindBoxId);
        if(blindBox.getExam_paper_id()!=null && blindBox.getStu_answer()==null){
            ExamPaper examPaper = examPaperService.selectById(blindBox.getExam_paper_id());
            ExamPaperEditRequestVM newVM1 = examPaperService.examPaperToVM(examPaper.getId());
            newVM1.setMention("您已抽过题，请开始答题吧！");
            return RestResponse.ok(newVM1);
        }else{
            if (questionService.selectAllCount() > 0) {
//                List<Integer> questionNumbers = questionService.findAllQuestionIds();
                List<Integer> questionNumbers = questionService.findSingleQuestions(); //抽盲盒只抽单选题
                System.out.println("题库中已有的题目号为：" + questionNumbers);
                Integer randomQuestionNumber = getRandomQuestionNumber(questionNumbers);
                System.out.println("随机选中的题号是：" + randomQuestionNumber);
                QuestionEditRequestVM questionVM = questionService.getQuestionEditRequestVM(randomQuestionNumber);
                //下面把抽到的一道题目设置为一套试卷
                ExamPaperEditRequestVM examPaperEditRequestVM = new ExamPaperEditRequestVM();
                examPaperEditRequestVM.setSubjectId(1); //这里学科该怎么设置成不同的呢？或者是对应的学科
                examPaperEditRequestVM.setPaperType(1);
                examPaperEditRequestVM.setCourseName(blindBox.getClass_name());
                examPaperEditRequestVM.setStuAccount(blindBox.getStu_account());
                //还没有校验学生在不在班级，班级有没有存在
                examPaperEditRequestVM.setName(createNewName(blindBox.getClass_name(), blindBox.getStu_account()));
                examPaperEditRequestVM.setSuggestTime(2);

                List<ExamPaperTitleItemVM> titleItems = new ArrayList<>();
                ExamPaperTitleItemVM item1 = new ExamPaperTitleItemVM();
                item1.setName("题目");
                List<QuestionEditRequestVM> questionList = new ArrayList<>();
                questionList.add(questionVM);
                item1.setQuestionItems(questionList);
                titleItems.add(item1);
                examPaperEditRequestVM.setTitleItems(titleItems);
                Student student = studentService.get_detail_by_account(blindBox.getStu_account());
                ExamPaper examPaper = examPaperService.savePaperFromVM_stu(examPaperEditRequestVM, student);
                ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
                // 在blind_box中插入相关数据
//            BlindBox blindBox = new BlindBox(coursename,stuaccount, examPaper.getId(), teacher_account);
//            blindBoxMapper.insert(blindBox);
                QueryWrapper queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",blindBoxId);
                BlindBox blindBox1 = new BlindBox(examPaper.getId());
                blindBoxMapper.update(blindBox1,queryWrapper);
                return RestResponse.ok(newVM);
            }
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
    @ApiOperation("盲盒老师或者学生查看盲盒记录，查看该老师获学所有的盲盒记录，老师端就只需要输入课程名和老师账户，学生就输入课程名和学生账户")
    @PostMapping("blindbox_view_history")
    public RestResponse<PageInfo<ViewHistoryVM>> blindbox_view_history(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = true) String class_name, @RequestParam(required = false) String stu_account, @RequestParam(required = false) String teacher_account){
        PageInfoBlindBoxRequestVM model = new PageInfoBlindBoxRequestVM();
        model.setClass_name(class_name);
        model.setStu_account(stu_account);
        model.setTeacher_account(teacher_account);
        model.setPageIndex(pageIndex);
        model.setPageSize(pageSize);
        PageInfo<BlindBox> pageInfo =  blindBoxService.blindBoxPage(model);//这里就将请求的model带入进行查询了
        PageInfo<ViewHistoryVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ViewHistoryVM vm = modelMapper.map(e, ViewHistoryVM.class);
            String stu_name = studentService.getStuNameByStuAccount(vm.getStu_account());
            vm.setStu_name(stu_name);
            if(vm.getExam_paper_id()!=null){ //必须满足抽题了有试卷才能写入题目
                ExamPaperEditRequestVM vm1 = examPaperService.examPaperToVM(vm.getExam_paper_id());
                String question_title = vm1.getTitleItems().get(0).getQuestionItems().get(0).getTitle();
                System.out.println(question_title);
                vm.setQuestion_title(question_title);
            }

            return vm;
        });
        return RestResponse.ok(page);
    }


    @ResponseBody
    @ApiOperation("盲盒某一条记录删除，点击某一条记录，输入该条记录的id")
    @PostMapping("blindbox_delete")
    public ResposeResult blindbox_delete(@RequestParam Integer id){
        //这里仅仅是删除盲盒记录，但是和盲盒记录关联的学生答题试卷记录和答题试卷并没有删除
        try{
            blindBoxMapper.deleteById(id);
//            blindBoxMapper.delete_by_id_and_delete_exam(id);
            return new ResposeResult(1, "删除成功");
        }catch (Exception e){
            return new ResposeResult(0, "删除失败");
        }
    }

    @ResponseBody
    @ApiOperation("学生查看老师盲盒是否抽到自己")
    @PostMapping("view_result")
    public List<BlindBox> viewResult(@RequestParam String stuAccount){
        return blindBoxService.view_result(stuAccount);
    }


}
