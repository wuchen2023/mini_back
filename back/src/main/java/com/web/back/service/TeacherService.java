package com.web.back.service;

import com.web.back.domain.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.TeacherGroupResult;

import java.util.List;

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

    public String get_invite_code_by_course_name(String course_name);

    public ResposeResult add_student_points(Integer points, String student_id, String course_name);

    public List<StudentPoints> get_points_sort(String course_name);

    public List<Teacher> get_all_teacher();

    public ResposeResult<TeacherGroupResult> create_group_task(Group group, Integer teacher_id);

    public ResposeResult add_group_of_number(StudentGroup studentGroup);

    public List<TeacherSignIn> get_all_qiandao(Integer teacher_class_id);

    public List<TeacherClass> get_all_class_by_teacher_id(Integer teacher_id);

    public List<StudentGroup> get_all_student_group_by_teacher_group_id(Integer teacher_group_id);

    public List<TeacherGroup> get_all_teacher_group_task(Integer teacher_id);

    public Group get_group_by_group_id(Integer group_id);

}
