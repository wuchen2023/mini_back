package com.web.back.viewmodel.student.question.answer;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class QuestionPageStudentRequestVM extends BasePage {
    private Integer createUser;

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}
