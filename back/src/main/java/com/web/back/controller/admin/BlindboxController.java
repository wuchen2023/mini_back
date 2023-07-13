package com.web.back.controller.admin;

import com.alibaba.druid.support.logging.Log;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.Student;
import com.web.back.domain.StudentClass;
import com.web.back.service.QuestionService;
import com.web.back.service.StudentClassService;
import com.web.back.service.StudentService;
import com.web.back.service.TextContentService;
import com.web.back.state.RestResponse;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassResponseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    private final StudentClassService studentClassService;

    @Autowired
    public BlindboxController(QuestionService questionService, TextContentService textContentService, StudentService studentService, StudentClassService studentClassService) {
        this.questionService = questionService;
        this.textContentService = textContentService;
        this.studentService = studentService;
        this.studentClassService = studentClassService;
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
        String name =studentService.student_id_get_name(student_id);
        StudentClassResponseVM studentClassResponseVM = new StudentClassResponseVM();
        studentClassResponseVM.setName(name);
        studentClassResponseVM.setClass_name(className);

        return RestResponse.ok(studentClassResponseVM);
    }


    /**
     * 传入老师标识码和账户验证老师登录，然后调用盲盒接口开始抽题，
     *
     * @param code
     * @param teacher_account
     * @return
     */
    @ResponseBody
    @ApiOperation("随机抽一道题")
    @PostMapping("blindbox")
    public RestResponse<QuestionEditRequestVM> blindbox(@RequestParam String code, @RequestParam String teacher_account) {
        if (code.equals(redis_get(teacher_account))) {
            System.out.println("查询的结果是：" + questionService.selectAllCount());
            if (questionService.selectAllCount() > 0) {
                List<Integer> questionNumbers = questionService.findAllQuestionIds();
                System.out.println("题库中已有的题目号为："+questionNumbers);
                Integer randomQuestionNumber = getRandomQuestionNumber(questionNumbers);
                System.out.println("随机选中的题号是：" + randomQuestionNumber);
                QuestionEditRequestVM newVM = questionService.getQuestionEditRequestVM(randomQuestionNumber);
                return RestResponse.ok(newVM);
            } else {
                return RestResponse.fail(400, "题目数量为0!!!");
            }
        } else {
            return RestResponse.fail(400, "未登录，请登录！");
        }

    }

    //按照传入的题目列表大小随机在这写数中选择一个id
//    public static int getRandomId(int id) {
//        Random random = new Random();
//        int MAX = id, MIN = 4;
//        int number = random.nextInt(MAX - MIN + 1) + MIN;
//        System.out.println("随机生成的数字是：" + number);
//        return number;
//    }

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

}
