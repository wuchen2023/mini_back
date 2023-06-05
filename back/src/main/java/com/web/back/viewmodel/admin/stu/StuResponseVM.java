package com.web.back.viewmodel.admin.stu;

import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.web.back.utils.DateTimeUtil;
import com.web.back.viewmodel.admin.BaseVM;
import com.web.back.viewmodel.admin.user.UserResponseVM;

/**
 * @author by hongdou
 * @date 2023/6/5.
 * @DESC:
 */
public class StuResponseVM extends BaseVM {
    private Integer id;

    private String name;

    private String permission;

    private String account;

    private String createTime;

    private String gender;

    private Integer role;

    public  static StuResponseVM from(Student student){
        StuResponseVM vm = modelMapper.map(student, StuResponseVM.class);
        vm.setCreateTime(DateTimeUtil.dateFormat(student.getCreateTime()));
        return vm;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }
}
