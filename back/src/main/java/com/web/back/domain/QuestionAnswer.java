package com.web.back.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public class QuestionAnswer implements Serializable {
    private static final long serialVersionUID = 4339489705502953315L;

    private Integer id;

    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 题型
     */
    private Integer questionType;

    /**
     * 学科
     */
    private Integer subjectId;

    /**
     * 得分
     */
    private Integer customerScore;

    /**
     * 题目原始得分
     */
    private Integer questionScore;

    /**
     * 问题内容
     */
    private Integer questionTextContentId;

    /**
     * 做题答案
     */
    private String answer;

    /**
     * 做题内容（存储简答题的做题内容）
     */
    private Integer textContentId;

    /**
     * 是否正确
     */
    private Boolean doRight;

    /**
     * 做题人
     */
    private Integer createUser;

    private Date createTime;

    private Integer itemOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }


    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCustomerScore() {
        return customerScore;
    }

    public void setCustomerScore(Integer customerScore) {
        this.customerScore = customerScore;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public Integer getQuestionTextContentId() {
        return questionTextContentId;
    }

    public void setQuestionTextContentId(Integer questionTextContentId) {
        this.questionTextContentId = questionTextContentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getTextContentId() {
        return textContentId;
    }

    public void setTextContentId(Integer textContentId) {
        this.textContentId = textContentId;
    }

    public Boolean getDoRight() {
        return doRight;
    }

    public void setDoRight(Boolean doRight) {
        this.doRight = doRight;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }
}
