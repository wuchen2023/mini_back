package com.web.back.config.spring.security;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public class AuthenticationBean {

    private String userName;
    private String password;

    private boolean remember;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
