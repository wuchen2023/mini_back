package com.web.back.viewmodel.admin.debate1;

import io.swagger.models.auth.In;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public  class StudentInfoVM {
    private Integer id; //grouping表中id

    private Integer studentId;

    private Integer studentGroupId;

    private String student_name;

    public StudentInfoVM(Integer id, Integer studentId, Integer studentGroupId, String student_name) {
        this.id  =id;
        this.studentId = studentId;
        this.studentGroupId = studentGroupId;
        this.student_name = student_name;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getStudentId(){
        return studentId;
    }

    public void setStudentId(Integer studentId){
        this.studentId =studentId;
    }

    public Integer getStudentGroupId(){
        return studentGroupId;
    }

    public String getStudent_name(){
        return student_name;
    }

    public void setStudent_name(String student_name){
        this.student_name = student_name;
    }

}