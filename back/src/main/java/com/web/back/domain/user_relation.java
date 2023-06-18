package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_relation
 */
@TableName(value ="user_relation")
@Data
public class user_relation implements Serializable {
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
    private Integer friendId;

    /**
     * 
     */
    private Integer identity;

    public user_relation(Integer userId, Integer friendId, Integer identity) {
        this.userId = userId;
        this.friendId = friendId;
        this.identity = identity;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}