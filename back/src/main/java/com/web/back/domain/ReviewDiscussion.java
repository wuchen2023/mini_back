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
 * @TableName review_discussion
 */
@TableName(value ="review_discussion")
@Data
@AllArgsConstructor
public class ReviewDiscussion implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer discussionId;

    /**
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private String reviewStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}