package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Activity;
import com.web.back.domain.result.ActicityRes;
import com.web.back.service.ActivityService;
import com.web.back.mapper.ActivityMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Dell
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2023-05-07 13:09:23
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

    @Resource
    ActivityMapper activityMapper;

    @Override
    public ResposeResult add_activity(String activity_type, Integer teacher_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_type", activity_type);
            queryWrapper.eq("teacher_id", teacher_id);
            queryWrapper.eq("course_name", course_name);

            Activity activity = activityMapper.selectOne(queryWrapper);
            if(activity != null)
            {
                throw new Exception();
            }

            Activity activity1 = new Activity(activity_type, teacher_id, course_name);
            activityMapper.insert(activity1);
            return new ResposeResult(1, "创建pk任务成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "创建pk任务失败");
        }
    }

    @Override
    public List<ActicityRes> get_all_activity_by_teacher_id(Integer teacher_id, String course_name) {
        return activityMapper.get_all_activity(teacher_id, course_name);
    }
}




