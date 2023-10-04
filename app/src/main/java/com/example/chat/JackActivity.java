package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public class JackActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "Lily.db";
    private static final String TABLE_NAME = "Jackmessage";

    private RecyclerView recyclerView;
    private TextView button_send;
    private TextView headlinetext;
    private ChatDBhelper dbhelper;
    private ChatAdapter adapter;
    private EditText editText;
    private ImageView deleteButton;
    private ImageView backButton;
    private List<LilyMsg> msgList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jack);

        headlinetext=findViewById(R.id.chat_headline_text);
        recyclerView=findViewById(R.id.chat_recycler_view);
        button_send=findViewById(R.id.send_button);
        editText=findViewById(R.id.message_edit_text);
        deleteButton=findViewById(R.id.delete_button);
        backButton=findViewById(R.id.back_button);
        dbhelper = new ChatDBhelper(JackActivity.this);

        headlinetext.setText("Jack");

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(JackActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    //数据添加
                    boolean flag = dbhelper.insertData(content, TABLE_NAME);
                    if (flag) {
                        editText.setText("");
                        Toast.makeText(JackActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        init();
                    } else {
                        Toast.makeText(JackActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = dbhelper.deleteAllData(TABLE_NAME);
                if (flag) {
                    Toast.makeText(JackActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    msgList.clear();
                    init();
                } else {
                    Toast.makeText(JackActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JackActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        init();
    }

    private void init() {
        getLatestMessageList();
        setupAdapter();
    }

    private void getLatestMessageList() {
        msgList.clear();
        msgList.addAll(dbhelper.query(TABLE_NAME));
    }

    private void setupAdapter() {
        if (adapter == null) {
            adapter = new ChatAdapter(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
        adapter.setMessageList(msgList);
    }
}