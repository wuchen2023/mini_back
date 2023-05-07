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
 * @TableName teacher_student
 */
@TableName(value ="teacher_student")
@Data
@AllArgsConstructor
public class TeacherStudent implements Serializable {
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
    private Integer studentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}