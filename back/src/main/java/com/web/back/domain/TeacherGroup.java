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
 * @TableName teacher_group
 */
@TableName(value ="teacher_group")
@Data
@AllArgsConstructor
public class TeacherGroup implements Serializable {
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
    private Integer groupId;

    private String course_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public TeacherGroup(Integer teacherId, Integer groupId, String course_name) {
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.course_name = course_name;
    }
}