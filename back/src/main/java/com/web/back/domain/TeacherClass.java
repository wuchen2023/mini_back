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
 * @TableName teacher_class
 */
@TableName(value ="teacher_class")
@Data
@AllArgsConstructor
public class TeacherClass implements Serializable {
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
    private String courseName;

    /**
     * 
     */
    private String classInviteCode;

    /**
     * 
     */
    private Date classCreateTime;

    /**
     * 
     */
    private Integer courseIsFinished;

    /**
     * 
     */
    private Integer courseStudentCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}