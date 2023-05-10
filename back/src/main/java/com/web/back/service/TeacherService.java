package com.web.back.service;

import com.web.back.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.TeacherClass;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【teacher】的数据库操作Service
* @createDate 2023-05-07 13:10:23
*/
public interface TeacherService extends IService<Teacher> {
    public ResposeResult add_teacher(Teacher teacher);

    public ResposeResult teacher_login(String account, String password);

    public ResposeResult create_course(TeacherClass teacherClass);

    public ResposeResult get_classId_by_name(String course_name);

    public ResposeResult close_qiandao(String sign_in_title);

    public Teacher get_detail_by_account(String account);

}
