package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName pk
 */
@TableName(value ="pk")
@Data
@AllArgsConstructor
public class Pk implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer activityId;

    /**
     * 
     */
    private Integer studentId1;

    /**
     * 
     */
    private Integer studentId2;

    /**
     * 
     */
    private Integer isFinished;

    private String course_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Pk(Integer activityId, Integer studentId1, Integer studentId2, Integer isFinished, String course_name) {
        this.activityId = activityId;
        this.studentId1 = studentId1;
        this.studentId2 = studentId2;
        this.isFinished = isFinished;
        this.course_name = course_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getStudentId1() {
        return studentId1;
    }

    public void setStudentId1(Integer studentId1) {
        this.studentId1 = studentId1;
    }

    public Integer getStudentId2() {
        return studentId2;
    }

    public void setStudentId2(Integer studentId2) {
        this.studentId2 = studentId2;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}