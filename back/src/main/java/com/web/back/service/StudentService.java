package com.web.back.service;

import com.web.back.domain.Grouping;
import com.web.back.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.StudentClass;
import com.web.back.state.ResposeResult;

import java.util.List;

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

    public ResposeResult add_course(Integer student_id, String invite_code);

    public Integer get_teacher_sign_in_id(String sign_in_title);

    public Student get_detail_by_account(String account);

    public List<Student> get_all_students();

    public String student_id_get_name(Integer student_id);

    public ResposeResult add_group(Grouping grouping, Integer group_number);

    public ResposeResult get_qiandao_state(Integer student_id, Integer teacher_sign_in_id);

    public List<Grouping> get_all_group_member(Integer student_group_id);

    public List<StudentClass> get_all_class_by_student_id(Integer student_id);
}
