package com.xxbeam.DTO;

public class MessageDTO {

    /**
     * 聊天id
     */
    private String chatId;

    /**
     * 消息
     */
    private String msg;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
