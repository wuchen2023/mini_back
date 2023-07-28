package com.web.back.viewmodel.admin.post;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
public class PostPageRequestVM extends BasePage {
    private  Integer id;
    private String title;
    private String content;

    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
