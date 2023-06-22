package com.web.back.domain;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class ExamPaperAnswerInfo {
    public ExamPaper examPaper;
    public ExamPaperAnswer examPaperAnswer;
    public List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers;

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }

    public ExamPaperAnswer getExamPaperAnswer() {
        return examPaperAnswer;
    }

    public void setExamPaperAnswer(ExamPaperAnswer examPaperAnswer) {
        this.examPaperAnswer = examPaperAnswer;
    }

    public List<ExamPaperQuestionCustomerAnswer> getExamPaperQuestionCustomerAnswers() {
        return examPaperQuestionCustomerAnswers;
    }

    public void setExamPaperQuestionCustomerAnswers(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers) {
        this.examPaperQuestionCustomerAnswers = examPaperQuestionCustomerAnswers;
    }
}
