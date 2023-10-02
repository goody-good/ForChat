package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.LilyAdapter;
import com.example.chat.LilyDBhelper;
import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public class LilyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView button_send;
    private LilyDBhelper lilyDBhelper;
    private LilyAdapter lilyAdapter;
    private EditText lilyEditText;
    private List<LilyMsg> lilyMsgList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lily);

        lilyDBhelper=new LilyDBhelper(LilyActivity.this,"Lily.db",null,1);
        recyclerView=findViewById(R.id.recyclerView_chat);
        button_send=findViewById(R.id.button_send);
        lilyEditText=findViewById(R.id.edit_message);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content1=lilyEditText.getText().toString().trim();
                if(TextUtils.isEmpty(content1)){
                    Toast.makeText(LilyActivity.this,"内容为空",Toast.LENGTH_SHORT).show();
                }else{
                    //数据添加
                    boolean flag=lilyDBhelper.insertData(content1);
                    if(flag){
                        lilyEditText.setText("");
                        Toast.makeText(LilyActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LilyActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                    init();
                }
            }
        });
        init();
    }
    private void init(){
        lilyMsgList.clear();
        lilyMsgList.addAll(lilyDBhelper.query());
        lilyAdapter=new LilyAdapter(LilyActivity.this,lilyMsgList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lilyAdapter);
    }
}