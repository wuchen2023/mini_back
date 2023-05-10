package com.web.back.viewmodel.student.question;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public class QuestionAnswerPageRequestVM extends BasePage {
    private Integer createUser;

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}
