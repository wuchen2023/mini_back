package com.web.back.utils;

import java.util.UUID;

public class GetOnlyCode {
    public String get_code()
    {
        UUID uuid = UUID.randomUUID();
        long time = System.currentTimeMillis();
        return uuid.toString() + time;
    }
}
