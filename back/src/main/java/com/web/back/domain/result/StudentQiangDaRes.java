package com.web.back.domain.result;


import lombok.Data;

@Data
public class StudentQiangDaRes {
    Integer qiangda_id;

    String student_name;

    String qiangda_type;

    String question;

    String reference_answer;


    String stu_answer;

    String is_right;

    public StudentQiangDaRes() {
    }

    public StudentQiangDaRes(Integer qiangda_id, String student_name, String qiangda_type, String question, String reference_answer, String stu_answer, String is_right) {
        this.qiangda_id = qiangda_id;
        this.student_name = student_name;
        this.qiangda_type = qiangda_type;
        this.question = question;
        this.reference_answer = reference_answer;
        this.stu_answer = stu_answer;
        this.is_right = is_right;
    }
}
