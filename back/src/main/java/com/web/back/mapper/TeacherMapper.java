package com.web.back.mapper;

import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.viewmodel.admin.user.UserPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2023-05-07 13:10:23
* @Entity generator.domain.Teacher
*/
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {


    List<Teacher> teacherPage(UserPageRequestVM requestVM);

    Teacher getTeacherByName(String name);

    int updateByPrimaryKeySelective(Teacher record);
    int insertSelective(Teacher record);

    Teacher getTeacherById(Integer id);

    Teacher selectByPrimaryKey(Integer id);
}




