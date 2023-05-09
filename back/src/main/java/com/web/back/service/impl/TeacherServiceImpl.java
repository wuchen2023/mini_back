package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.TeacherClass;
import com.web.back.mapper.TeacherClassMapper;
import com.web.back.service.TeacherService;
import com.web.back.domain.Teacher;
import com.web.back.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}




