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
 * @TableName student_points
 */
@TableName(value ="student_points")
@Data
@AllArgsConstructor
public class StudentPoints implements Serializable {
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
    private String courseName;

    /**
     * 
     */
    private Integer points;

    @TableField(exist = false)
    private String student_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public StudentPoints(Integer studentId, String courseName, Integer points) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.points = points;
    }

    public StudentPoints(Integer id, Integer studentId, String courseName, Integer points) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.points = points;
    }
}