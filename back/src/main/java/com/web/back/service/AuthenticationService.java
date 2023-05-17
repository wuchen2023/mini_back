package com.web.back.service;

import com.web.back.domain.Teacher;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public interface AuthenticationService {

    /**
     *
     * @param account 实际上是老师的账户名
     * @param password 老师的账户密码
     * @return
     */
    boolean authUser(String account, String password);

    boolean authUser(Teacher teacher, String account, String password);

    String pwdEncode(String password);

    String pwdDecode(String endodePwd);

}
