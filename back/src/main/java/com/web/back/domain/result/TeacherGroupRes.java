package com.web.back.domain.result;


import lombok.Data;

import java.util.Date;

@Data
public class TeacherGroupRes {
    private Integer id;
    private Integer teacherId;
    private Integer groupId;

    private String groupType;
    private Date groupTime;

    public TeacherGroupRes(Integer id, Integer teacherId, Integer groupId, String groupType) {
        this.id = id;
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.groupType = groupType;
//        this.groupTime = groupTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Date getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Date groupTime) {
        this.groupTime = groupTime;
    }
}
