package com.web.back.domain.result;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public class DebateWinnerRes {

    String group_name;

    Integer activity_id;

    String activity_type;

    public DebateWinnerRes() {
    }

    public DebateWinnerRes(String group_name, Integer activity_id, String activity_type) {
        this.group_name= group_name;
        this.activity_id = activity_id;
        this.activity_type = activity_type;
    }

    public String getGroup_name(){
        return group_name;
    }

    public void setGroup_name(String group_name){
        this.group_name = group_name;
    }
    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }
}
