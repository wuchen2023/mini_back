package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName discussion
 */
@TableName(value ="discussion")
@Data
@AllArgsConstructor
public class Discussion implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String discussionTopic;

    /**
     * 
     */
    private Date publishTime;

    /**
     * 
     */
    private Integer isReviewed;

    /**
     * 
     */
    private String reviewStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}