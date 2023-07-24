package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName qiangda
 */
@TableName(value ="qiangda")
@Data
public class Qiangda implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private String qiangdaType;

    /**
     * 
     */
    private String courseName;

    /**
     * 
     */
    private Integer isFinished;

    /**
     * 
     */
    private Date createTime;

    private String question;

    private String reference_answer;

    private String stu_answer;

    private String is_right;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Qiangda() {
    }

    public Qiangda(Integer id, Integer teacherId, String qiangdaType, String courseName, Integer isFinished, Date createTime,String  question, String reference_answer, String stu_answer, String is_right) {
        this.id = id;
        this.teacherId = teacherId;
        this.qiangdaType = qiangdaType;
        this.courseName = courseName;
        this.isFinished = isFinished;
        this.createTime = createTime;
        this.question = question;
        this.reference_answer = reference_answer;
        this.stu_answer= stu_answer;
        this.is_right = is_right;

    }

    public Qiangda(Integer teacherId, String qiangdaType, String courseName, Integer isFinished, String  question, String reference_answer) {
        this.teacherId = teacherId;
        this.qiangdaType = qiangdaType;
        this.courseName = courseName;
        this.isFinished = isFinished;
        this.question = question;
        this.reference_answer = reference_answer;
    }

    public Qiangda(String stu_answer){
        this.stu_answer =stu_answer;
    }

    public Qiangda(Integer id, String is_right){
        this.id = id;
        this.is_right =is_right;
    }

    public String getReference_answer() {
        return reference_answer;
    }

    public void setReference_answer(String reference_answer) {
        this.reference_answer = reference_answer;
    }


}