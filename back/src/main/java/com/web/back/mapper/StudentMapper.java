package com.web.back.mapper;

import com.web.back.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.viewmodel.admin.stu.StuPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-05-07 13:10:05
* @Entity generator.domain.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> studentPage(StuPageRequestVM requestVM);

}




