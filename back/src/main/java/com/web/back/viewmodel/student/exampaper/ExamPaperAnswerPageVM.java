package com.web.back.viewmodel.student.exampaper;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class ExamPaperAnswerPageVM extends BasePage {

    private Integer subjectId;

    private Integer createUser;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}