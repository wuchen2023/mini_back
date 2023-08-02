package com.web.back.viewmodel.admin.blindbox;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/8/2.
 * @DESC:
 */
public class PageInfoBlindBoxRequestVM extends BasePage {
    private  String class_name;

    private String stu_account;

    private String teacher_account;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }

    public String getTeacher_account() {
        return teacher_account;
    }

    public void setTeacher_account(String teacher_account) {
        this.teacher_account = teacher_account;
    }
}
