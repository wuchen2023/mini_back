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
 * @TableName pk
 */
@TableName(value ="pk")
@Data
@AllArgsConstructor
public class Pk implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer activityId;

    /**
     * 
     */
    private Integer studentId1;

    /**
     * 
     */
    private Integer studentId2;

    /**
     * 
     */
    private Integer isFinished;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}