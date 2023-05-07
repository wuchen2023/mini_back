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
 * @TableName blind_box
 */
@TableName(value ="blind_box")
@Data
@AllArgsConstructor
public class BlindBox implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String topicType;

    /**
     * 
     */
    private Integer quantity;

    /**
     * 
     */
    private Date extractTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}