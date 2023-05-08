package com.web.back.domain;

import java.io.Serializable;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 * @TableName:lib_subject
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = 6187228802357353891L;

    private Integer id;

    /**
     * 学科名称
     */
    private String name;

    /**
     * 对应年级，数字指代
     */
    private Integer level;

    /**
     * 对应年级名称
     */
    private String levelName;

    /**
     * 排序
     */
    private Integer itemOrder;

    private Boolean deleted;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
