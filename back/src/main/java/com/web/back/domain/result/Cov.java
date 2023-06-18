package com.web.back.domain.result;

import lombok.Data;

@Data
public class Cov {
    Integer id;
    String name;

    String identity;

    public Cov(Integer id, String name, String identity) {
        this.id = id;
        this.name = name;
        this.identity = identity;
    }
}
