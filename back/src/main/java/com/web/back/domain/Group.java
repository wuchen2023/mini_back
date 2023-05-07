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
 * @TableName group
 */
@TableName(value ="group")
@Data
@AllArgsConstructor
public class Group implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer groupSize;

    /**
     * 
     */
    private Date groupTime;

    /**
     * 
     */
    private String groupType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}