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
 * @TableName student
 */
@TableName(value ="student")
@Data
@AllArgsConstructor
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String permission;

    /**
     * 
     */
    private String account;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String gender;

    /**
     * 学生的角色，与web端有关的字段
     */
    private Integer role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Student(String name, String permission, String account, String password, String gender, Integer role) {
        this.name = name;
        this.permission = permission;
        this.account = account;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public Student(){

    }
}