package com.web.back.domain.other;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public class ExamPaperAnswerUpdate {

    private Integer id;
    private Integer customerScore;
    private Boolean doRight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerScore() {
        return customerScore;
    }

    public void setCustomerScore(Integer customerScore) {
        this.customerScore = customerScore;
    }

    public Boolean getDoRight() {
        return doRight;
    }

    public void setDoRight(Boolean doRight) {
        this.doRight = doRight;
    }
}
