package com.web.back.viewmodel.admin.paper;

import com.web.back.base.BasePage;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class ExamPaperAnswerPageRequestVM extends BasePage {
    private Integer subjectId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}