package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseChatActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected TextView button_send;
    protected EditText editText;
    protected ChatAdapter baseadapter;
    protected ChatDBhelper baseDBhelper;
    protected ImageView deleteButton;
    protected ImageView backButton;
    protected List <LilyMsg> msgList = new ArrayList<>();
    protected Context context;
    protected String TableName;

    protected abstract String getTableName();
    protected abstract Context getContext();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_chat);

        context=getContext();
        recyclerView=findViewById(R.id.chat_recycler_view);
        button_send=findViewById(R.id.send_button);
        editText=findViewById(R.id.message_edit_text);
        deleteButton=findViewById(R.id.delete_button);
        backButton=findViewById(R.id.back_button);
        TableName = getTableName();
        baseDBhelper = new ChatDBhelper(context);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(context, "内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    boolean flag = baseDBhelper.insertData(content, TableName);
                    if (flag) {
                        editText.setText("");
                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                        init();
                    } else {
                        Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = baseDBhelper.deleteAllData(TableName);
                if (flag) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    msgList.clear();
                    init();
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        init();
    }
    protected void handleSendButtonClick(){
        String content = editText.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(context, "内容为空", Toast.LENGTH_SHORT).show();
        } else {
            boolean flag = baseDBhelper.insertData(content, TableName);
            if (flag) {
                editText.setText("");
                Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                init();
            } else {
                Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void handleDeleteButtonClick() {
        boolean flag = baseDBhelper.deleteAllData(TableName);
        if (flag) {
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
            msgList.clear();
            init();
        } else {
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }
    protected void handleBackButtonClick() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void init() {
        getLatestMessageList();
        setupAdapter();
    }

    public void getLatestMessageList() {
        msgList.clear();
        msgList.addAll(baseDBhelper.query(TableName));
    }

    public void setupAdapter() {
        if (baseadapter == null) {
            baseadapter = new ChatAdapter(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(baseadapter);
        }
        baseadapter.setMessageList(msgList);
    }
}