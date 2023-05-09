package com.web.back.service;

import com.web.back.domain.StudentSignIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【student_sign_in】的数据库操作Service
* @createDate 2023-05-07 13:10:20
*/
public interface StudentSignInService extends IService<StudentSignIn> {

    public ResposeResult add_qiandao(StudentSignIn studentSignIn);

}
