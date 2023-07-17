package com.web.back.viewmodel.admin.activity;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public class ActivityRespVM {
    private Integer id;

    private String course_name;

    private Integer teacher_id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getCourse_name(){
        return course_name;
    }

    public void setCourse_name(String course_name){
        this.course_name = course_name;
    }
    public Integer getTeacher_id(){
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id){
        this.teacher_id = teacher_id;
    }
}
