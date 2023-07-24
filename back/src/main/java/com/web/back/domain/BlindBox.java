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
 * @TableName blind_box
 */
@TableName(value ="blind_box")
@Data
@AllArgsConstructor
public class BlindBox implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String stu_account;

    /**
     * 
     */
    private Integer exam_paper_id;
    private Integer is_right;

    private String true_answer;

    private String teacher_account;

    private String class_name;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public BlindBox(String stu_account, Integer exam_paper_id, Integer is_right, String true_answer, String teacher_account, String class_name){
        this.stu_account =stu_account;
        this.exam_paper_id = exam_paper_id;
        this.is_right = is_right;
        this.true_answer = true_answer;
        this.teacher_account = teacher_account;
        this.class_name = class_name;
    }

    public BlindBox(String coursename, String stuaccount, Integer examPaper_id, String teacher_account){
        this.class_name =coursename;
        this.stu_account = stuaccount;
        this.exam_paper_id = examPaper_id;
        this.teacher_account = teacher_account;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getStu_account(){
        return stu_account;
    }

    public void setStu_account(String stu_account){
        this.stu_account = stu_account;
    }


    public Integer getExam_paper_id(){
        return exam_paper_id;
    }

    public void setExam_paper_id(Integer exam_paper_id){
        this.exam_paper_id = exam_paper_id;
    }

    public Integer getIs_right(){
        return is_right;
    }

    public void setIs_right(Integer is_right){
        this.is_right = is_right;
    }
    public String getTrue_answer(){
        return true_answer;
    }

    public void setTrue_answer(String true_answer){
        this.true_answer = true_answer;
    }

    public String getTeacher_account(){
        return teacher_account;
    }

    public void setTeacher_account(String teacher_account){
        this.teacher_account  = teacher_account;
    }

    public String getClass_name(){
        return class_name;
    }

    public void setClass_name(String class_name){
        this.class_name =class_name;
    }
}