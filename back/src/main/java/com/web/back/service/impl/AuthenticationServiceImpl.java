package com.web.back.service.impl;

import com.web.back.config.property.SystemConfig;
import com.web.back.domain.Teacher;
import com.web.back.service.AuthenticationService;
import com.web.back.service.TeacherService;
import com.web.back.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TeacherService teacherService;

    private final SystemConfig systemConfig;

    @Autowired
    public AuthenticationServiceImpl(TeacherService teacherService, SystemConfig systemConfig){
        this.teacherService = teacherService;
        this.systemConfig = systemConfig;
    }

    @Override
    public boolean authUser(String account, String password){
        Teacher teacher = teacherService.get_detail_by_account(account);
        return authUser(teacher, account, password);
    }

    @Override
    public boolean authUser(Teacher teacher, String account, String password) {
        if (teacher == null) {
            return false;
        }
        String encodePwd = teacher.getPassword();
        if (null == encodePwd || encodePwd.length() == 0) {
            return false;
        }
//        String pwd = pwdDecode(encodePwd);
//        return pwd.equals(password);
        return encodePwd.equals(password);
    }


    @Override
    public String pwdEncode(String password) {
        return RsaUtil.rsaEncode(systemConfig.getPwdKey().getPublicKey(), password);
    }

    @Override
    public String pwdDecode(String encodePwd) {
        return RsaUtil.rsaDecode(systemConfig.getPwdKey().getPrivateKey(), encodePwd);
    }
}
