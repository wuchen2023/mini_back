package com.web.back.mapper;

import com.web.back.domain.TeacherGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.TeacherGroupRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【teacher_group】的数据库操作Mapper
* @createDate 2023-05-07 13:10:28
* @Entity generator.domain.TeacherGroup
*/
@Mapper
public interface TeacherGroupMapper extends BaseMapper<TeacherGroup> {


    @Select("select teacher_group.* , `group`.group_type, `group`.group_time from teacher_group, `group` where teacher_group.teacher_id = #{teacher_id} and teacher_group.course_name = #{course_name} and `group`.id = teacher_group.group_id")
    List<TeacherGroupRes> getListWithGroupType(Integer teacher_id, String course_name);



}




