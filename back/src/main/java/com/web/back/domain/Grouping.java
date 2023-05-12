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
 * @TableName grouping
 */
@TableName(value ="grouping")
@Data
@AllArgsConstructor
public class Grouping implements Serializable {
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
    private Integer studentGroupId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Grouping(Integer studentId, Integer studentGroupId) {
        this.studentId = studentId;
        this.studentGroupId = studentGroupId;
    }
}