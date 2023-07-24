package com.web.back.controller;


import com.web.back.domain.StudentQiangda;
import com.web.back.domain.result.StudentQiangDaRes;
import com.web.back.service.StudentQiangdaService;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.qiangda.StuAddQiangDaVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("学生抢答Api")
public class StudentQiangDaController {

    @Resource
    StudentQiangdaService studentQiangdaService;

    @ResponseBody
    @ApiOperation("学生进行抢答")
    @PostMapping("add_student_qiangda")
    public StuAddQiangDaVM add_student_qiangda(@RequestParam Integer student_id, @RequestParam Integer qiangda_id, @RequestParam String course_name)
    {
        StudentQiangda studentQiangda = new StudentQiangda(student_id, qiangda_id, course_name);
        return studentQiangdaService.add_student_qiangda(studentQiangda);
    }

    @ResponseBody
    @ApiOperation("获取抢答结果")
    @PostMapping("get_qiangda_res")
    public StudentQiangDaRes get_qiangda_res(@RequestParam Integer qiangda_id, @RequestParam String course_name)
    {
        return studentQiangdaService.get_qiangda_res(qiangda_id, course_name);
    }

    @ResponseBody
    @ApiOperation("学生回答抢答内容")
    @PostMapping("qiangda_submit")
    public ResposeResult qiangda_submit(@RequestParam Integer qiangda_id,@RequestParam String stu_answer){
        return studentQiangdaService.qiangda_submit(qiangda_id, stu_answer);
    }
}
