package com.web.back.viewmodel.admin.education;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public class SubjectPageRequestVM extends BasePage {
    private Integer id;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
