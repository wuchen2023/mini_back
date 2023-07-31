package com.web.back.mapper;

import com.web.back.domain.Student;
import com.web.back.domain.StudentSignIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【student_sign_in】的数据库操作Mapper
* @createDate 2023-05-07 13:10:20
* @Entity generator.domain.StudentSignIn
*/
public interface StudentSignInMapper extends BaseMapper<StudentSignIn> {

    @Select("select student.* from student, student_sign_in where student.id = student_sign_in.student_id and student_sign_in.teacher_sign_in_id = #{teacher_sign_in_id}")
    List<Student> get_all_student_has_sign_in(Integer teacher_sign_in_id);

}




