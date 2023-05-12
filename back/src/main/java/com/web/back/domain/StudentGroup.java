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
 * @TableName student_group
 */
@TableName(value ="student_group")
@Data
@AllArgsConstructor
public class StudentGroup implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String groupName;

    /**
     * 
     */
    private Integer teacherGroupId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public StudentGroup(String groupName, Integer teacherGroupId) {
        this.groupName = groupName;
        this.teacherGroupId = teacherGroupId;
    }
}