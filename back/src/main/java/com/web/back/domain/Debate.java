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
 * @TableName debate
 */
@TableName(value ="debate")
@Data
@AllArgsConstructor
public class Debate implements Serializable {
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
    private String debateTopic;

    /**
     * 
     */
    private Integer debateSize;

    /**
     * 
     */
    private Integer isFinished;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}