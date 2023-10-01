package com.example.chat.javabean;

public class myMessage {
    private String content;
    private String friendId;

    public myMessage(String friendId, String content){
        this.friendId=friendId;
        this.content=content;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
