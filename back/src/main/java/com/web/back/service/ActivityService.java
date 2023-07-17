package com.web.back.service;

import com.web.back.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.ActicityRes;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.viewmodel.admin.activity.ActivityRespVM;

import java.util.List;

/**
* @author Dell
* @description 针对表【activity】的数据库操作Service
* @createDate 2023-05-07 13:09:23
*/
public interface ActivityService extends IService<Activity> {

    public ResposeResult add_activity_pk(String activity_type, Integer teacher_id, String course_name);
//    public RestResponse add_activity_debate(Integer teacher_id, String course_name);
    ActivityRespVM add_activity_debate(Integer teacher_id, String course_name);
    public List<ActicityRes> get_all_activity_by_teacher_id(Integer teacher_id, String course_name);

}
