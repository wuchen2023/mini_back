package com.web.back.mapper;

import com.web.back.domain.StudentPoints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【student_points】的数据库操作Mapper
* @createDate 2023-05-10 13:30:25
* @Entity com.web.back.domain.StudentPoints
*/
public interface StudentPointsMapper extends BaseMapper<StudentPoints> {

    @Select("select student_points.*, student.name from student, student_points where student.id = student_points.student_id and student_points.course_name=#{course_name} order by student_points.points desc")
    List<StudentPoints> get_points_sort(String course_name);

}




