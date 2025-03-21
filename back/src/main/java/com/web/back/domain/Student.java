package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.back.annotation.Excel;
import com.web.back.annotation.Excels;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @TableName student
 */
@TableName(value = "student")
@Data
@AllArgsConstructor
public class Student implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "序号")
    private Integer id;
    @Excel(name = "学号")
    private String account;
    /**
     *
     */
    @Excel(name = "姓名")
    private String name;

    /**
     *
     */
    private String permission;

    /**
     *
     */


    /**
     *
     */
    @Excel(name = "密码", type = Excel.Type.EXPORT)
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
    @Excel(name = "班级编号", type = Excel.Type.IMPORT)
    private Long subjectId;
    @Excels({
            @Excel(name = "班级名", targetAttr = "subjectName", type = Excel.Type.EXPORT),
            @Excel(name = "班级负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SubjectClass subjectClass;
//    private Boolean deleted;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
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



    public Student(Integer id){
        this.id = id;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Student() {

    }
//    public Boolean getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        this.deleted = deleted;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Student other = (Student) obj;
        return this.account.equals(other.getAccount());
    }

    public SubjectClass getSubjectClass() {
        return subjectClass;
    }


    public void setSubjectClass(SubjectClass subjectClass) {
        this.subjectClass = subjectClass;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("permission", getPermission())
                .append("account", getAccount())
                .append("createTime", getCreateTime())
                .append("gender", getGender())
                .append("role", getRole())
                .append("subjectId", getSubjectId())
                .append("subjectClass", getSubjectClass())
                .toString();
    }
}