package com.web.back.controller;


import com.web.back.domain.TeacherSignIn;
import com.web.back.service.StudentService;
import com.web.back.service.TeacherService;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("签到通用接口")
public class QianDaoController {
    @Resource
    TeacherService teacherService;

    @Resource
    StudentService studentService;
    @ResponseBody
    @ApiOperation("获取当前课程所有的签到列表")
    @GetMapping("get_all_qiandao_list")
    public List<TeacherSignIn> get_all_qiandao_list(@RequestParam Integer teacher_class_id)
    {
        return teacherService.get_all_qiandao(teacher_class_id);
    }

    @ResponseBody
    @ApiOperation("学生获取签到状态")
    @GetMapping("get_qiandao_state")
    public ResposeResult get_qiandao_state(@RequestParam Integer student_id,@RequestParam Integer teacher_sign_in_id) {
        return studentService.get_qiandao_state(student_id, teacher_sign_in_id);
    }

}
