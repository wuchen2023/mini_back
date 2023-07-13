package com.web.back.domain.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class StudentClassRes {
    private Integer id;

    /**
     *
     */
    private Integer studentId;

    /**
     *
     */
    private String className;

    /**
     *
     */
    private String classInviteCode;

    /**
     *
     */
    private Date joinClassTime;

    private Integer teacher_id;

    public StudentClassRes(Integer id, Integer studentId, String className, String classInviteCode, Date joinClassTime, Integer teacher_id) {
        this.id = id;
        this.studentId = studentId;
        this.className = className;
        this.classInviteCode = classInviteCode;
        this.joinClassTime = joinClassTime;
        this.teacher_id = teacher_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassInviteCode() {
        return classInviteCode;
    }

    public void setClassInviteCode(String classInviteCode) {
        this.classInviteCode = classInviteCode;
    }

    public Date getJoinClassTime() {
        return joinClassTime;
    }

    public void setJoinClassTime(Date joinClassTime) {
        this.joinClassTime = joinClassTime;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }
}
