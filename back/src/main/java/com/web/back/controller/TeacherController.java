package com.web.back.controller;


import com.web.back.domain.*;
import com.web.back.service.TeacherService;
import com.web.back.service.TeacherSignInService;
import com.web.back.state.ResposeResult;
import com.web.back.utils.GetOnlyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Api("老师的Api")
public class TeacherController {
    @Resource
    TeacherService teacherService;
    @Resource
    TeacherSignInService teacherSignInService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @ApiOperation("老师注册，添加")
    @PostMapping("add_teacher")
    public ResposeResult add_teacher(@RequestParam String name, @RequestParam String account, @RequestParam String password, @RequestParam String gender)
    {
        Teacher teacher = new Teacher(name, "0", account, password, gender);
        return teacherService.add_teacher(teacher);
    }


    @ResponseBody
    @GetMapping("login_teacher")
    @ApiOperation("老师登录，获取唯一的标识码")
    public ResposeResult teacher_login(@RequestParam String account, @RequestParam String password)
    {
        ResposeResult re = teacherService.teacher_login(account, password);
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
    @ApiOperation("老师创建班级")
    @PostMapping("create_course")
    public ResposeResult create_course(@RequestParam Integer teacher_id, @RequestParam String course_name ,@RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            GetOnlyCode getOnlyCode = new GetOnlyCode();
            String invite_code = getOnlyCode.get_invite_code();
            TeacherClass teacherClass = new TeacherClass(teacher_id, course_name, invite_code, 0);
            ResposeResult resposeResult = teacherService.create_course(teacherClass);
            resposeResult.setMessage(invite_code);
            return resposeResult;
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("老师发放签到")
    @PostMapping("create_qiandao")
    public ResposeResult create_qiandao(@RequestParam Integer teacher_class_id, @RequestParam String sign_in_title, @RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            TeacherSignIn teacherSignIn = new TeacherSignIn(teacher_class_id, 0, sign_in_title, 1);
            return teacherSignInService.create_qiandao(teacherSignIn);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("老师根据课程名字查询teacher_class_id")
    @GetMapping("get_class_id_by_name")
    public ResposeResult get_class_id_by_name(@RequestParam String course_name)
    {
        return teacherService.get_classId_by_name(course_name);
    }

    @ResponseBody
    @ApiOperation("根据签到名称关闭签到")
    @GetMapping("close_qiandao_by_title")
    public ResposeResult close_qiandao(@RequestParam String sign_in_title, @RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            return teacherService.close_qiandao(sign_in_title);
        }
        else {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("根据老师的账户获取老师信息")
    @GetMapping("get_teacher_detail")
    public Teacher get_detail(@RequestParam  String account, @RequestParam String code)
    {
        if(code.equals(redis_get(account)))
        {
            return teacherService.get_detail_by_account(account);
        }
        else
        {
            return null;
        }
    }

    @ResponseBody
    @ApiOperation("根据课程名称查询邀请码")
    @GetMapping("get_invite_code")
    public String get_invite_code_by_course_name(@RequestParam String course_name)
    {
        return teacherService.get_invite_code_by_course_name(course_name);
    }

    @ResponseBody
    @ApiOperation("给学生加分")
    @PostMapping("add_student_pounts")
    public ResposeResult add_student_points(@RequestParam Integer points, @RequestParam String student_id, @RequestParam String course_name, @RequestParam String teacher_account, @RequestParam String code)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            return teacherService.add_student_points(points, student_id, course_name);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("获取学生积分排名，降序")
    @GetMapping("get_points_sort")
    public List<StudentPoints> get_points_sort(@RequestParam String course_name)
    {
        return teacherService.get_points_sort(course_name);
    }


    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-teacher", value, 7, TimeUnit.DAYS);
    }
    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-teacher");
    }


}
