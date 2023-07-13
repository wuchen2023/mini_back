package com.web.back.viewmodel.student.exam;

import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;

/**
 * @author by hongdou
 * @date 2023/7/13.
 * @DESC:
 */
public class ExamPaperReadVM {
    private ExamPaperEditRequestVM paper;
    private ExamPaperSubmitVM answer;

    public ExamPaperEditRequestVM getPaper() {
        return paper;
    }

    public void setPaper(ExamPaperEditRequestVM paper) {
        this.paper = paper;
    }

    public ExamPaperSubmitVM getAnswer() {
        return answer;
    }

    public void setAnswer(ExamPaperSubmitVM answer) {
        this.answer = answer;
    }

}
