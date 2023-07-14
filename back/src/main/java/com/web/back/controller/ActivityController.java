package com.web.back.controller;


import com.web.back.domain.result.ActicityRes;
import com.web.back.service.ActivityService;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("Pk的Api")
public class ActivityController {

    @Resource
    ActivityService activityService;


    @ResponseBody
    @ApiOperation("老师创建pk活动")
    @PostMapping("add_activity")
    public ResposeResult add_activity(@RequestParam String activity_type, @RequestParam Integer teacher_id)
    {
        return activityService.add_activity(activity_type, teacher_id);
    }

    @ResponseBody
    @ApiOperation("根据teacher_id获取所有pk的活动")
    @PostMapping("get_all_activity")
    public List<ActicityRes> get_all_activity(@RequestParam Integer teacher_id)
    {
        return activityService.get_all_activity_by_teacher_id(teacher_id);
    }

}
