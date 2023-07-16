package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_qiangda
 */
@TableName(value ="student_qiangda")
@Data
public class StudentQiangda implements Serializable {
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
    private Integer qiangdaId;

    /**
     * 
     */
    private String courseName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public StudentQiangda(Integer studentId, Integer qiangdaId, String courseName) {
        this.studentId = studentId;
        this.qiangdaId = qiangdaId;
        this.courseName = courseName;
    }

    public StudentQiangda() {
    }

    public StudentQiangda(Integer id, Integer studentId, Integer qiangdaId, String courseName) {
        this.id = id;
        this.studentId = studentId;
        this.qiangdaId = qiangdaId;
        this.courseName = courseName;
    }
}