package com.web.back.mapper;

import com.web.back.domain.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.ActicityRes;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【activity】的数据库操作Mapper
* @createDate 2023-05-07 13:09:23
* @Entity generator.domain.Activity
*/
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("SELECT activity.id as activity_id, activity.teacher_id, activity.activity_type, teacher.`name` as teacher_name FROM `activity`, `teacher` where activity.teacher_id = #{teacher_id} and teacher.id = #{teacher_id}")
    List<ActicityRes> get_all_activity(Integer teacher_id);

}




