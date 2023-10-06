package com.example.chat.javabean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LilyMsg {
    private String id;
    private String content;
    private String timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
