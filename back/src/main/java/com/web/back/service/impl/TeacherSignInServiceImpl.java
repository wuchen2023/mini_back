package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.StudentClass;
import com.web.back.domain.TeacherClass;
import com.web.back.domain.TeacherSignIn;
import com.web.back.service.TeacherSignInService;
import com.web.back.mapper.TeacherSignInMapper;
import com.web.back.state.ResposeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Dell
* @description 针对表【teacher_sign_in】的数据库操作Service实现
* @createDate 2023-05-09 15:05:19
*/
@Service
@Slf4j
public class TeacherSignInServiceImpl extends ServiceImpl<TeacherSignInMapper, TeacherSignIn>
    implements TeacherSignInService{

    @Resource
    TeacherSignInMapper teacherSignInMapper;

    @Override
    public ResposeResult create_qiandao(TeacherSignIn teacherSignIn) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("teacher_class_id", teacherSignIn.getTeacherClassId());
            queryWrapper.eq("sign_in_title", teacherSignIn.getSignInTitle());
            TeacherSignIn teacherSignIn1 = teacherSignInMapper.selectOne(queryWrapper);
            //第一步检查这个主题的签到是否已经有了
            if(teacherSignIn1 != null)
            {
                throw new Exception();
            }
            teacherSignInMapper.insert(teacherSignIn);
            log.info(teacherSignIn.getSignInTitle() + "签到创建成功！");
        }catch (Exception e)
        {
            log.info("签到创建失败");
            return new ResposeResult(0 , "创建签到失败");
        }
        return new ResposeResult(1, "创建签到成功");
    }
}




