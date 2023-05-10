package com.web.back.controller;


import com.web.back.domain.Student;
import com.web.back.domain.StudentSignIn;
import com.web.back.domain.Teacher;
import com.web.back.service.StudentService;
import com.web.back.service.StudentSignInService;
import com.web.back.state.ResposeResult;
import com.web.back.utils.GetOnlyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@Api("学生的Api")
public class StudentController {
    @Resource
    StudentService studentService;

    @Resource
    StudentSignInService studentSignInService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @ResponseBody
    @PostMapping("add_student")
    @ApiOperation("添加学生,注册")
    public ResposeResult add_student(@RequestParam String name, @RequestParam String account, @RequestParam String password, @RequestParam String gender)
    {
        Student student = new Student(name, "0", account, password, gender);
        return studentService.add_student(student);
    }




    //登录后，学生的信息讲纳入缓存，标识码讲纳入缓存
    @ResponseBody
    @GetMapping ("login_student")
    @ApiOperation("学生登录，获取唯一的标识码")
    public ResposeResult student_login(@RequestParam String account, @RequestParam String password)
    {
        ResposeResult re = studentService.student_login(account, password);
        if (re.getCode() == 0)
        {
            return new ResposeResult(0, "登录失败");
        }
        else
        {
            GetOnlyCode getOnlyCode = new GetOnlyCode();
            String only_code = redis_get(account);
            if(only_code == null)
            {
                only_code = getOnlyCode.get_code();
                redis_save(account, only_code);
                return new ResposeResult(1, only_code);
            }
            else
            {
                return new ResposeResult(1, only_code);
            }
        }
    }

    @ResponseBody
    @PostMapping("add_course")
    @ApiOperation("学生加入班级")
    public ResposeResult add_course(@RequestParam Integer student_id, @RequestParam String invite_code, @RequestParam String code, @RequestParam String student_account)
    {
        if(code.equals(redis_get(student_account)))
        {
            return studentService.add_course(student_id, invite_code);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("学生签到")
    @PostMapping("add_qiandao")
    public ResposeResult add_qiandao(@RequestParam Integer student_id, @RequestParam String sign_in_title, @RequestParam String code, @RequestParam String student_account)
    {
        if(code.equals(redis_get(student_account)))
        {
            Integer teacher_sign_in_id = studentService.get_teacher_sign_in_id(sign_in_title);
            if(teacher_sign_in_id == -1)
            {
                return new ResposeResult(0 ,"签到失败");
            }
            StudentSignIn studentSignIn = new StudentSignIn(student_id, teacher_sign_in_id, "签到成功");
            return studentSignInService.add_qiandao(studentSignIn);
        }
        else{
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("根据学生的账户获取学生信息")
    @GetMapping("get_student_detail")
    public Student get_detail(@RequestParam  String account, @RequestParam String code)
    {
        if(code.equals(redis_get(account)))
        {
            return studentService.get_detail_by_account(account);
        }
        else
        {
            return null;
        }
    }
    @ResponseBody
    @ApiOperation("获取全部学生的信息")
    @GetMapping("get_all_students")
    public List<Student> get_all_students(){
        return studentService.get_all_students();
    }

    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-student", value, 7, TimeUnit.DAYS);
    }
    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-student");
    }


}
