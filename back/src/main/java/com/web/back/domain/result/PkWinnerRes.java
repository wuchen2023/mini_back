package com.web.back.domain.result;

import lombok.Data;

@Data
public class PkWinnerRes {
    String winner_name;

    Integer activity_id;

    String activity_type;

    public PkWinnerRes() {
    }

    public PkWinnerRes(String winner_name, Integer activity_id, String activity_type) {
        this.winner_name = winner_name;
        this.activity_id = activity_id;
        this.activity_type = activity_type;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
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
