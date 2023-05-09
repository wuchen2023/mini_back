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
 * @TableName student_class
 */
@TableName(value ="student_class")
@Data
@AllArgsConstructor
public class StudentClass implements Serializable {
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
    private String className;

    /**
     * 
     */
    private String classInviteCode;

    /**
     * 
     */
    private Date joinClassTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public StudentClass(Integer studentId, String className, String classInviteCode) {
        this.studentId = studentId;
        this.className = className;
        this.classInviteCode = classInviteCode;
    }
}