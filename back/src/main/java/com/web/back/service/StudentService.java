package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.Grouping;
import com.web.back.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.StudentClass;
import com.web.back.domain.Teacher;
import com.web.back.domain.result.StudentClassRes;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.stu.StuPageRequestVM;

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

    public Student student_id_get_name(Integer student_id);

    public ResposeResult add_group(Grouping grouping, Integer group_number, Integer group_id);

    public ResposeResult exit_group(Integer student_id, Integer student_group_id, Integer group_id);

    public ResposeResult get_qiandao_state(Integer student_id, Integer teacher_sign_in_id);

    public List<Grouping> get_all_group_member(Integer student_group_id);

    public List<StudentClassRes> get_all_class_by_student_id(Integer student_id);

    public List<List<Student>> get_qiandao_detail(String course_name, Integer teacher_sign_in_id);

    /**
     * 下面是管理端相关的
     */
    PageInfo<Student> studentPage(StuPageRequestVM requestVM);


    Student getStuByStuName(String name);
    String  getStuNameByStuAccount(String stuAccount);

    int updateByIdFilter(Student record);

    //插入的操作
    int insertByFilter(Student record);

    //实现删除
    boolean deleteById(Integer id);

    public String importUser(List<Student> userList, Boolean isUpdateSupport);
    public List<Student> selectStudentList(Student student);

    public List<Student> newselectStudentList(Student student);


}
