package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pk_winer
 */
@TableName(value ="pk_winer")
@Data
public class PkWiner implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 
     */
    private Integer activityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public PkWiner(Integer studentId, Integer activityId) {
        this.studentId = studentId;
        this.activityId = activityId;
    }
}