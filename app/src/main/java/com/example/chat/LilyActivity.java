package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.LilyAdapter;
import com.example.chat.LilyDBhelper;
import com.example.chat.javabean.LilyMsg;

import java.util.List;

public class LilyActivity extends AppCompatActivity {
    private ListView listView;
    private TextView button_send;
    private LilyDBhelper lilyDBhelper;
    private LilyAdapter lilyAdapter;
    private EditText lilyEditText;
    private List<LilyMsg> lilyMsgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lily);
        LilyDBhelper lilyDBhelper=new LilyDBhelper(LilyActivity.this,"Lily.db",null,1);
        listView=findViewById(R.id.recyclerView_chat);
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
                    LilyDBhelper lilyDBhelper=new LilyDBhelper(LilyActivity.this,"Lily.db",null,1);
                    boolean flag=lilyDBhelper.insertData(content1);
                    if(flag){
                        Toast.makeText(LilyActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LilyActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        init();
    }
    private void init(){
        if(lilyMsgList!=null){
            lilyMsgList.clear();
        }
        lilyDBhelper=new LilyDBhelper(LilyActivity.this,"Lily.db",null,1);
        lilyMsgList=lilyDBhelper.query();
        lilyAdapter=new LilyAdapter(LilyActivity.this,lilyMsgList);
        listView.setAdapter(lilyAdapter);
    }
}