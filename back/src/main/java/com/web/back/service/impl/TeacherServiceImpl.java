package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.*;
import com.web.back.mapper.StudentPointsMapper;
import com.web.back.mapper.TeacherClassMapper;
import com.web.back.mapper.TeacherSignInMapper;
import com.web.back.service.TeacherService;
import com.web.back.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.web.back.state.ResposeResult;
import javax.annotation.Resource;
import java.util.List;

/**
* @author Dell
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2023-05-07 13:10:23
*/
@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;
    @Resource
    TeacherClassMapper teacherClassMapper;

    @Resource
    TeacherSignInMapper teacherSignInMapper;

    @Resource
    StudentPointsMapper studentPointsMapper;

    @Override
    public ResposeResult add_teacher(Teacher teacher) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", teacher.getAccount());
            if(teacherMapper.exists(queryWrapper))
            {
                throw new Exception();
            }
            teacherMapper.insert(teacher);
            log.info("老师-注册id为" + teacher.getId());

        }catch (Exception e)
        {
            log.info("该用户已经存在,请重新尝试");
            return new ResposeResult(0, "注册失败");
        }
        return new ResposeResult(1, "注册成功");
    }

    @Override
    public ResposeResult teacher_login(String account, String password) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", account);
            Teacher teacher = teacherMapper.selectOne(queryWrapper);
            if(teacher == null)
            {
                throw new Exception();
            }
            if(password.equals(teacher.getPassword()))
            {
                log.info(teacher.getName() + "登录成功");
                return new ResposeResult(1 , "登录成功");
            }
            else {
                throw new Exception();
            }
        }catch (Exception e)
        {
            log.info("登录失败");
            return new ResposeResult(0 , "登录失败");
        }
    }

    @Override
    public ResposeResult create_course(TeacherClass teacherClass) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("course_name", teacherClass.getCourseName());
            if (teacherClassMapper.exists(queryWrapper)) {
                throw new Exception();
            }
            teacherClassMapper.insert(teacherClass);
            log.info("老师-创建班级id为" + teacherClass.getId());

        } catch (Exception e) {
            log.info("该班级已经存在,请重新尝试");
            return new ResposeResult(0, "创建失败");
        }
        return new ResposeResult(1, "创建成功");
    }

    @Override
    public ResposeResult get_classId_by_name(String course_name) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_name", course_name);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            return new ResposeResult(1, "" + teacherClass.getId());
        }catch (Exception e)
        {
            return new ResposeResult(0, "查询失败");
        }
    }

    @Override
    public ResposeResult close_qiandao(String sign_in_title) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sign_in_title", sign_in_title);
            TeacherSignIn teacherSignIn = teacherSignInMapper.selectOne(queryWrapper);
            if(teacherSignIn == null)
            {
                throw new Exception();
            }
            teacherSignIn.setIsValid(0);
            teacherSignInMapper.update(teacherSignIn, queryWrapper);
            return new ResposeResult(1, sign_in_title + "-签到关闭成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "关闭签到失败");
        }
    }

    @Override
    public Teacher get_detail_by_account(String account) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", account);
            Teacher teacher = teacherMapper.selectOne(queryWrapper);
            if(teacher == null)
            {
                throw new Exception();
            }
            teacher.setPassword("不可见");
            return teacher;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public String get_invite_code_by_course_name(String course_name) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_name", course_name);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            return teacherClass.getClassInviteCode();

        }catch (Exception e)
        {
            return "未查询到";
        }
    }

    @Override
    public ResposeResult add_student_points(Integer points, String student_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student_id);
            queryWrapper.eq("course_name", course_name);
            StudentPoints studentPoints = studentPointsMapper.selectOne(queryWrapper);
            if(studentPoints == null)
            {
                throw new Exception();
            }
            studentPoints.setPoints(studentPoints.getPoints() + points);
            studentPointsMapper.update(studentPoints, queryWrapper);
            return new ResposeResult(1, "加" + points + "分成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "加分失败");
        }
    }

    @Override
    public List<StudentPoints> get_points_sort(String course_name) {
        return studentPointsMapper.get_points_sort(course_name);
    }
}




