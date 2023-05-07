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
 * @TableName topic_blind_box
 */
@TableName(value ="topic_blind_box")
@Data
@AllArgsConstructor
public class TopicBlindBox implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer topicId;

    /**
     * 
     */
    private Integer blindBoxId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}