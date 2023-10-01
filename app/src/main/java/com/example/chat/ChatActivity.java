package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chat.javabean.myMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private ChatAdapter chatAdapter;
    private List<myMessage> messageList;
    private MyDBhelper myDBhelper;
    private String firendId;

    public void setFirendId(String firendId) {
        this.firendId = firendId;
    }

    public String getFirendId() {
        return firendId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        RecyclerView recyclerView=findViewById(R.id.recyclerView_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageList=new ArrayList<>();
        chatAdapter =new ChatAdapter(this,messageList);
        recyclerView.setAdapter(chatAdapter);
        myDBhelper=new MyDBhelper(this);
        loadChatHistory(firendId);

        TextView sendButton = findViewById(R.id.button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText messageEdiText=findViewById(R.id.edit_message);
                String content = messageEdiText.getText().toString().trim();
                myMessage message=new myMessage(firendId,content);
                // 将消息插入数据库
                myDBhelper.addMessage(message);
                // 更新聊天记录并清空输入框
                loadChatHistory(firendId);
                messageEdiText.setText("");
            }
        });
    }

    private void loadChatHistory(String friendId){
        // 从数据库查询与当前好友相关的聊天消息列表
        List<myMessage> messages = myDBhelper.getMessagesByFriendId(friendId);

        // 更新适配器的数据列表
        chatAdapter.updateMessageList(messages);
    }
}