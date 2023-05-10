package com.web.back.viewmodel.admin.studentclass;

import com.web.back.viewmodel.admin.BaseVM;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public class StudentClassResponseVM extends BaseVM {
    private Integer id;
    private Integer student_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

}
