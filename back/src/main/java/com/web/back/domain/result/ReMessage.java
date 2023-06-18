package com.web.back.domain.result;

import lombok.Data;

import java.util.Date;

@Data
public class ReMessage {
    Integer senderId;
    Integer receiverId;
    String receiverName;
    String content;
    Date sendTime;

    public ReMessage(Integer senderId, Integer receiverId, String receiverName, String content, Date sendTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.content = content;
        this.sendTime = sendTime;
    }
}
