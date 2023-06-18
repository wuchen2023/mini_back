package com.web.back.domain.result;


import lombok.Data;

@Data
public class Fridend {
    Integer id;
    String name;

    String identity;

    public Fridend(Integer id, String name, String identity) {
        this.id = id;
        this.name = name;
        this.identity = identity;
    }
}
