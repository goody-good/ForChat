package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.javabean.User;

public class RegisterActivity extends AppCompatActivity {
    private TextView register1;
    private EditText mobile1, password1;
    private MyDBhelper myDBhelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDBhelper1=new MyDBhelper(this);
        find();
    }

    private void find() {
        register1 = findViewById(R.id.buttonRegister);
        mobile1 = findViewById(R.id.RegisterMobile);
        password1 = findViewById(R.id.RegisterPassword);
    }

    public void zhuce(View view) {
        String s = mobile1.getText().toString();
        String s1 = password1.getText().toString();
        User u = new User(s, s1);
        long l = myDBhelper1.register(u);
        if (l != -1) {
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            Intent i3 = new Intent(this, LoginActivity.class);
            startActivity(i3);
        } else {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
        }
    }
    public void fanhui(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}