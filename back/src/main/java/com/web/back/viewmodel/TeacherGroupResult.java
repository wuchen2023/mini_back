package com.web.back.viewmodel;


import lombok.Data;

@Data
public class TeacherGroupResult {
    private Integer teacher_group_id;
    private Integer group_number;

    public TeacherGroupResult(Integer teacher_group_id, Integer group_number) {
        this.teacher_group_id = teacher_group_id;
        this.group_number = group_number;
    }
}
