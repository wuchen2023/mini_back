package com.web.back.viewmodel.admin.task;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class TaskPageRequestVM extends BasePage {
    private Integer gradeLevel;

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}

