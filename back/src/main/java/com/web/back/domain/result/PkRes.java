package com.web.back.domain.result;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PkRes {

    Integer activity_id;
    Integer student_id_1;
    String student_1_name;

    Integer student_id_2;
    String student_2_name;

    Integer is_finished;

    String course_name;

    Integer stu_item1;
    Integer stu_item2;

    public PkRes(Integer activity_id, Integer student_id_1, String student_1_name, Integer student_id_2, String student_2_name, Integer is_finished, String course_name, Integer stu_item1, Integer stu_item2) {
        this.activity_id = activity_id;
        this.student_id_1 = student_id_1;
        this.student_1_name = student_1_name;
        this.student_id_2 = student_id_2;
        this.student_2_name = student_2_name;
        this.is_finished = is_finished;
        this.course_name = course_name;
        this.stu_item1 = stu_item1;
        this.stu_item2 = stu_item2;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Integer getStudent_id_1() {
        return student_id_1;
    }

    public void setStudent_id_1(Integer student_id_1) {
        this.student_id_1 = student_id_1;
    }

    public String getStudent_1_name() {
        return student_1_name;
    }

    public void setStudent_1_name(String student_1_name) {
        this.student_1_name = student_1_name;
    }

    public Integer getStudent_id_2() {
        return student_id_2;
    }

    public void setStudent_id_2(Integer student_id_2) {
        this.student_id_2 = student_id_2;
    }

    public String getStudent_2_name() {
        return student_2_name;
    }

    public void setStudent_2_name(String student_2_name) {
        this.student_2_name = student_2_name;
    }

    public Integer getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Integer is_finished) {
        this.is_finished = is_finished;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getStu_item1(){
        return stu_item1;
    }

    public void setStu_item1(Integer stu_item1){
        this.stu_item1 = stu_item1;
    }

    public Integer getStu_item2(){
        return stu_item2;
    }

    public  void setStu_item2(Integer stu_item2){
        this.stu_item2 = stu_item2;
    }
}
