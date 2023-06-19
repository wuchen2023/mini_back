package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer senderId;

    /**
     * 
     */
    private Integer receiverId;

    /**
     * 
     */
    private Date time;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Integer identity;

    private Integer identity_sender;


    public Message(Integer senderId, Integer receiverId, String content, Integer identity, Integer identity_sender) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.identity = identity;
        this.identity_sender = identity_sender;
    }

    public Message(Integer id, Integer senderId, Integer receiverId, Timestamp time, String content, Integer identity, Integer identity_sender) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.time = time;
        this.content = content;
        this.identity = identity;
        this.identity_sender = identity_sender;
    }

}