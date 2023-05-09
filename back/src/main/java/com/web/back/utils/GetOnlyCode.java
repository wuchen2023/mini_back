package com.web.back.utils;

import java.util.UUID;

public class GetOnlyCode {
    public String get_code()
    {
        UUID uuid = UUID.randomUUID();
        long time = System.currentTimeMillis();
        return uuid.toString() + time;
    }

    public  String get_invite_code() {
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString().replace("-", "").substring(0, 8);
        return code;
    }
}
