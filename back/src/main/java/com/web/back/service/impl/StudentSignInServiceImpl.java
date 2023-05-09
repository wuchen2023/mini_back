package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.TeacherSignIn;
import com.web.back.mapper.TeacherSignInMapper;
import com.web.back.service.StudentSignInService;
import com.web.back.domain.StudentSignIn;
import com.web.back.mapper.StudentSignInMapper;
import com.web.back.state.ResposeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Dell
* @description 针对表【student_sign_in】的数据库操作Service实现
* @createDate 2023-05-07 13:10:20
*/
@Service
@Slf4j
public class StudentSignInServiceImpl extends ServiceImpl<StudentSignInMapper, StudentSignIn>
    implements StudentSignInService {

    @Resource
    StudentSignInMapper studentSignInMapper;

    @Resource
    TeacherSignInMapper teacherSignInMapper;


    @Override
    public ResposeResult add_qiandao(StudentSignIn studentSignIn) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", studentSignIn.getStudentId());
            queryWrapper.eq("teacher_sign_in_id", studentSignIn.getTeacherSignInId());
            StudentSignIn studentSignIn1 = studentSignInMapper.selectOne(queryWrapper);
            //第一步判断是否签到
            if(studentSignIn1 != null)
            {
                throw new Exception();
            }
            //进行签到
            studentSignInMapper.insert(studentSignIn);
            log.info(studentSignIn.getStudentId() + "签到成功！");
            //更新签到人数
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", studentSignIn.getTeacherSignInId());
            TeacherSignIn teacherSignIn = teacherSignInMapper.selectOne(queryWrapper1);
            teacherSignIn.setSignedInCount(teacherSignIn.getSignedInCount() + 1);
            teacherSignInMapper.update(teacherSignIn, queryWrapper1);
        }catch (Exception e)
        {
            log.info("签到失败");
            return new ResposeResult(0 , "签到失败");
        }
        return new ResposeResult(1, "签到成功");
    }
}




