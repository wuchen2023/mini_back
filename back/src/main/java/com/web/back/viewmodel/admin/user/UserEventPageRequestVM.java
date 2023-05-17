package com.web.back.viewmodel.admin.user;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public class UserEventPageRequestVM extends BasePage {
    /**
     * userId 实际上为老师的id
     */
    private Integer userId;

    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
