package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Activity;
import com.web.back.domain.result.ActicityRes;
import com.web.back.service.ActivityService;
import com.web.back.mapper.ActivityMapper;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.activity.ActivityRespVM;
import net.sf.jsqlparser.statement.select.ExceptOp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

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
    public ResposeResult add_activity_pk(String activity_type, Integer teacher_id, String course_name) {
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
    public ActivityRespVM add_activity_debate(Integer teacher_id, String course_name){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            String activity_type = generateDebateString(course_name);
            System.out.println(activity_type);
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
            System.out.println("新增活动的id为："+activity1.getId());
            ActivityRespVM newVM = new ActivityRespVM();
            newVM.setId(activity1.getId());
            newVM.setCourse_name(course_name);
            newVM.setTeacher_id(teacher_id);
            return newVM;
        }catch (Exception e){
            return null;
        }
    }

    public static String generateDebateString(String course_name) {
        // 生成随机的三位数
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;

        // 构建辩论会字符串
        StringBuilder sb = new StringBuilder();
        sb.append("辩论会_")
                .append(course_name)
                .append("_")
                .append(randomNumber);

        return sb.toString();
    }

    @Override
    public List<ActicityRes> get_all_activity_by_teacher_id(Integer teacher_id, String course_name) {
        return activityMapper.get_all_activity(teacher_id, course_name);
    }
}




