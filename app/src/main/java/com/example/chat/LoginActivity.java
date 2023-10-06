package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.javabean.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView login,register,forget;
    private EditText mobile,password;
    private MyDBhelper myDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDBhelper=new MyDBhelper(this);
        find();
    }
    private void find(){
        login=findViewById(R.id.buttonLogin);
        register=findViewById(R.id.turn_to_register_button);
        mobile=findViewById(R.id.inputMobile);
        password=findViewById(R.id.inputPassword);
        forget=findViewById(R.id.textForgetPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        int id =view.getId();
        if(id==R.id.buttonLogin){
            String s=mobile.getText().toString();
            String s1=password.getText().toString();
            boolean login=myDBhelper.login(s,s1);
            if(login){
                Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(this,"登录失败！",Toast.LENGTH_SHORT).show();
            }
        }else if(id==R.id.turn_to_register_button){
            Intent i1=new Intent(this,com.example.chat.RegisterActivity.class);
            startActivity(i1);
        } else if (id==R.id.textForgetPassword) {
            Intent i2=new Intent(this,com.example.chat.RecoverAcitvity.class);
            startActivity(i2);
        }
    }
}