package com.web.back.state;


import lombok.Data;

@Data
public class ResposeResult <T>{
    private int code;
    private String message;

    private T respose;

    public ResposeResult(int code, String message, T respose) {
        this.code = code;
        this.message = message;
        this.respose = respose;
    }

    public ResposeResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
