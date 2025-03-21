package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.TeacherSignIn;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【teacher_sign_in】的数据库操作Service
* @createDate 2023-05-09 15:05:19
*/
public interface TeacherSignInService extends IService<TeacherSignIn> {
    public ResposeResult create_qiandao(TeacherSignIn teacherSignIn);

}
