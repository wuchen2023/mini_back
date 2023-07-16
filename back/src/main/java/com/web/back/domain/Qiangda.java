package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName qiangda
 */
@TableName(value ="qiangda")
@Data
public class Qiangda implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private String qiangdaType;

    /**
     * 
     */
    private String courseName;

    /**
     * 
     */
    private Integer isFinished;

    /**
     * 
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Qiangda() {
    }

    public Qiangda(Integer id, Integer teacherId, String qiangdaType, String courseName, Integer isFinished, Date createTime) {
        this.id = id;
        this.teacherId = teacherId;
        this.qiangdaType = qiangdaType;
        this.courseName = courseName;
        this.isFinished = isFinished;
        this.createTime = createTime;
    }

    public Qiangda(Integer teacherId, String qiangdaType, String courseName, Integer isFinished) {
        this.teacherId = teacherId;
        this.qiangdaType = qiangdaType;
        this.courseName = courseName;
        this.isFinished = isFinished;
    }
}