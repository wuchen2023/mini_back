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
 * @TableName teacher_sign_in
 */
@TableName(value ="teacher_sign_in")
@Data
@AllArgsConstructor
public class TeacherSignIn implements Serializable {
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
    private Integer teacherClassId;

    /**
     * 
     */
    private Date signInTime;

    /**
     * 
     */
    private Date deadlineTime;

    /**
     * 
     */
    private Integer isValid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}