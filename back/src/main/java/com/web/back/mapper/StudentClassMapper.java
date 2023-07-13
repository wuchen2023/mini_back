package com.web.back.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.back.domain.StudentClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.StudentClassRes;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【student_class】的数据库操作Mapper
* @createDate 2023-05-07 13:10:07
* @Entity generator.domain.StudentClass
*/
@Mapper
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    @Select("select student_class.*, teacher_class.teacher_id from student_class,teacher_class where student_class.student_id = #{student_id} and teacher_class.course_name = student_class.class_name")
    List<StudentClassRes> get_all_class(Integer student_id);

    List<StudentClass> page(StudentClassPageRequestVM requestVM);

    StudentClass get_student(String className);
}




