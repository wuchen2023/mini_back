package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.StudentService;
import com.web.back.domain.Student;
import com.web.back.mapper.StudentMapper;
import com.web.back.state.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Dell
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-05-07 13:10:05
*/
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public ResponseResult add_student(Student student) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", student.getAccount());
            if(studentMapper.exists(queryWrapper))
            {
                throw new Exception();
            }
            studentMapper.insert(student);
            log.info("学生-注册id为" + student.getId());

        }catch (Exception e)
        {
            log.info("该用户已经存在,请重新尝试");
            return new ResponseResult(0, "注册失败");
        }
        return new ResponseResult(1, "注册成功");
    }

    @Override
    public ResponseResult student_login(String account, String password) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", account);
            Student student = studentMapper.selectOne(queryWrapper);
            if(student == null)
            {
                throw new Exception();
            }
            if(password.equals(student.getPassword()))
            {
                log.info(student.getName() + "登录成功");
                return new ResponseResult(1 , "登录成功");
            }
            else {
                throw new Exception();
            }
        }catch (Exception e)
        {
            log.info("登录失败");
            return new ResponseResult(0 , "登录失败");
        }
    }
}




