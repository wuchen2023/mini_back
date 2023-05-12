package com.web.back.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.back.domain.StudentClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【student_class】的数据库操作Mapper
* @createDate 2023-05-07 13:10:07
* @Entity generator.domain.StudentClass
*/
@Mapper
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    List<StudentClass> page(StudentClassPageRequestVM requestVM);

    StudentClass get_student(String className);
}




