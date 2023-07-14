package com.web.back.controller;


import com.web.back.domain.Pk;
import com.web.back.domain.result.PkRes;
import com.web.back.domain.result.PkWinnerRes;
import com.web.back.service.PkService;
import com.web.back.service.PkWinerService;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("Pk相关接口")
public class PkController {

    @Resource
    PkService pkService;

    @ResponseBody
    @ApiOperation("创建相关activity的pk")
    @PostMapping("add_pk")
    public ResposeResult add_pk(@RequestParam Integer activity_id, @RequestParam String course_name)
    {
        return pkService.add_pk(activity_id, course_name);
    }

    @ResponseBody
    @ApiOperation("关闭一个activity的pk")
    @PostMapping("close_pk")
    public ResposeResult close_pk(@RequestParam Integer activity_id, @RequestParam String course_name)
    {
        return pkService.close_pk(activity_id, course_name);
    }

    @ResponseBody
    @ApiOperation("获取当前班级的所有pk")
    @PostMapping("get_all_pk")
    public List<PkRes> get_all_pk(@RequestParam String course_name)
    {
        return pkService.get_all_pk(course_name);
    }

    @Resource
    PkWinerService pkWinerService;

    @ResponseBody
    @ApiOperation("添加pk胜者")
    @PostMapping("add_pk_winner")
    public ResposeResult add_pk_winner(@RequestParam Integer student_id, @RequestParam Integer activity_id)
    {
        return pkWinerService.add_pk_winner(student_id, activity_id);
    }

    @ResponseBody
    @ApiOperation("获取胜者信息")
    @PostMapping("get_winner")
    public PkWinnerRes get_winner(@RequestParam Integer activity_id)
    {
        return pkWinerService.get_winner(activity_id);
    }



}
