package com.web.back.viewmodel.admin.studentclass;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public class StudentClassPageRequestVM extends BasePage {
    private Integer id;
    private String className;

    public Integer getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(){
        this.className = className;
    }
}
