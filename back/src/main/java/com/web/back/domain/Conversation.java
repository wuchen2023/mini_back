package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName conversation
 */
@TableName(value ="conversation")
@Data
public class Conversation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer chatId;

    /**
     * 
     */
    private Integer identity;

    public Conversation(Integer userId, Integer chatId, Integer identity) {
        this.userId = userId;
        this.chatId = chatId;
        this.identity = identity;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}