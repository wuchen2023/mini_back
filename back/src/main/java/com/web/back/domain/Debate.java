package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName debate
 */
@TableName(value ="debate")
@Data
@AllArgsConstructor
public class Debate implements Serializable {
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
    private String debateTopic;

    /**
     * 
     */
    /**
     * 
     */
    private Integer isFinished;

    private Integer positive_side_student_group_id;

    private Integer negative_side_student_group_id;

    private String course_name;

    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Debate(Integer activityId, Integer isFinished,  Integer positive_side_student_group_id, Integer negative_side_student_group_id, String course_name, String debateTopic){
        this.activityId = activityId;
        this.debateTopic = debateTopic;
        this.isFinished = isFinished;
        this.positive_side_student_group_id = positive_side_student_group_id;
        this.negative_side_student_group_id = negative_side_student_group_id;
        this.course_name = course_name;
        this.create_time = new Date();
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDebateTopic(){
        return debateTopic;
    }

    public void setDebateTopic(String debateTopic){
        this.debateTopic = debateTopic;
    }

    public Integer getActivityId(){
        return activityId;
    }

    public void setActivityId(Integer activityId){
        this.activityId = activityId;
    }

    public Integer getIsFinished(){
        return isFinished;
    }

    public void setIsFinished(Integer isFinished){
        this.isFinished = isFinished;
    }

    public Integer getPositive_side_student_group_id(){
        return positive_side_student_group_id;
    }

    public void setPositive_side_student_group_id(Integer positive_side_student_group_id){
        this.positive_side_student_group_id = positive_side_student_group_id;
    }

    public Integer getNegative_side_student_group_id(){
        return negative_side_student_group_id;
    }

    public void setNegative_side_student_group_id(Integer negative_side_student_group_id){
        this.negative_side_student_group_id = negative_side_student_group_id;
    }

    public String getCourse_name(){
        return course_name;
    }

    public void setCourse_name(String course_name){
        this.course_name = course_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}