package com.web.back.domain.result;


import lombok.Data;

@Data
public class StudentQiangDaRes {
    Integer qiangda_id;

    String student_name;

    String qiangda_type;

    public StudentQiangDaRes() {
    }

    public StudentQiangDaRes(Integer qiangda_id, String student_name, String qiangda_type) {
        this.qiangda_id = qiangda_id;
        this.student_name = student_name;
        this.qiangda_type = qiangda_type;
    }
}
