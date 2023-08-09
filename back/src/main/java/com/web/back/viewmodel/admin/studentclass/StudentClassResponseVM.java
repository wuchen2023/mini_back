package com.web.back.viewmodel.admin.studentclass;

import com.web.back.viewmodel.admin.BaseVM;

import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public class StudentClassResponseVM extends BaseVM {
//    private Integer id;
//    private Integer student_id;
    private String stu_name;

    private String class_name;

    private String stu_account;

    private Date create_time;

    public StudentClassResponseVM() {
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getClass_name(){
        return class_name;
    }

    public void setClass_name(String class_name){
        this.class_name = class_name;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
