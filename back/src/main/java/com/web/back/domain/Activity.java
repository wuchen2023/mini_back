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
 * @TableName activity
 */
@TableName(value ="activity")
@Data
@AllArgsConstructor
public class Activity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String activityType;

    /**
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private Date createTime;

    private String course_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Activity(String activityType, Integer teacherId, String course_name) {
        this.activityType = activityType;
        this.teacherId = teacherId;
        this.course_name = course_name;
    }
}