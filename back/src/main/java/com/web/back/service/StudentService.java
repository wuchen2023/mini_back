package com.web.back.service;

import com.web.back.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【student】的数据库操作Service
* @createDate 2023-05-07 13:10:05
*/
public interface StudentService extends IService<Student> {

    //学生的注册
    public ResposeResult add_student(Student student);

    //学生的登录

    public ResposeResult student_login(String account, String password);
}
