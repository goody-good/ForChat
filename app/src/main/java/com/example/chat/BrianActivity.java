package com.example.chat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public class BrianActivity extends AppCompatActivity {
    private static final String TABLE_NAME = "Brianmessage";
    private static final String DATABASE_NAME = "Lily.db";
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
        setContentView(R.layout.activity_tom);

        headlinetext=findViewById(R.id.chat_headline_text);
        recyclerView=findViewById(R.id.chat_recycler_view);
        button_send=findViewById(R.id.send_button);
        editText=findViewById(R.id.message_edit_text);
        deleteButton=findViewById(R.id.delete_button);
        backButton=findViewById(R.id.back_button);
        dbhelper = new ChatDBhelper(BrianActivity.this);

        headlinetext.setText("Brian");

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(BrianActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    boolean flag = dbhelper.insertData(content, TABLE_NAME);
                    if (flag) {
                        editText.setText("");
                        Toast.makeText(BrianActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        init();
                    } else {
                        Toast.makeText(BrianActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(BrianActivity.this);
                builder.setTitle("删除聊天记录")
                        .setMessage("你真的没有一点留恋吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean flag = dbhelper.deleteAllData(TABLE_NAME);
                                if (flag) {
                                    Toast.makeText(BrianActivity.this, "往事随云烟，薄奠", Toast.LENGTH_SHORT).show();
                                    msgList.clear();
                                    init();
                                } else {
                                    Toast.makeText(BrianActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog = builder.create();
                dialog.show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrianActivity.this, MainActivity.class);
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
            adapter = new ChatAdapter(BrianActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
        adapter.setMessageList(msgList);
    }
}