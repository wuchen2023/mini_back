package com.web.back.viewmodel.admin.blindbox;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/7/24.
 * @DESC:
 */
public class ViewHistoryVM {
    private Integer id;

    /**
     *
     */
    private String stu_name;

    private String stu_account;
    private String teacher_account;

    private Integer exam_paper_id;
    private Integer is_right;

    private String true_answer;



    private String stu_answer;
    private String class_name;

    private String question_title;

    private Date create_time;
    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }


    public Integer getExam_paper_id() {
        return exam_paper_id;
    }

    public void setExam_paper_id(Integer exam_paper_id) {
        this.exam_paper_id = exam_paper_id;
    }

    public Integer getIs_right() {
        return is_right;
    }

    public void setIs_right(Integer is_right) {
        this.is_right = is_right;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    public String getTeacher_account() {
        return teacher_account;
    }

    public void setTeacher_account(String teacher_account) {
        this.teacher_account = teacher_account;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStu_answer() {
        return stu_answer;
    }

    public void setStu_answer(String stu_answer) {
        this.stu_answer = stu_answer;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
