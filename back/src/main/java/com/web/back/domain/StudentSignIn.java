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
 * @TableName student_sign_in
 */
@TableName(value ="student_sign_in")
@Data
@AllArgsConstructor
public class StudentSignIn implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 
     */
    private Integer teacherSignInId;

    /**
     * 
     */
    private String signInStatus;

    /**
     * 
     */
    private Date signInTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public StudentSignIn(Integer studentId, Integer teacherSignInId, String signInStatus) {
        this.studentId = studentId;
        this.teacherSignInId = teacherSignInId;
        this.signInStatus = signInStatus;
    }
}