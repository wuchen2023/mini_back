package com.web.back.viewmodel.admin.stu;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/6/5.
 * @DESC:
 */
public class StuPageRequestVM extends BasePage {
    private String account;

    private Integer role;

    public String getAccount(){
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRole(){
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
