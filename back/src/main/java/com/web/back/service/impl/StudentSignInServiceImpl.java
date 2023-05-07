package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.StudentSignInService;
import com.web.back.domain.StudentSignIn;
import com.web.back.mapper.StudentSignInMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【student_sign_in】的数据库操作Service实现
* @createDate 2023-05-07 13:10:20
*/
@Service
public class StudentSignInServiceImpl extends ServiceImpl<StudentSignInMapper, StudentSignIn>
    implements StudentSignInService {

}




