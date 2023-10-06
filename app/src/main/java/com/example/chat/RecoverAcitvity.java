package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.javabean.User;

public class RecoverAcitvity extends AppCompatActivity implements View.OnClickListener{
    private TextView recover, return_to_login;
    private EditText mobile, password;
    private MyDBhelper myDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        myDBhelper = new MyDBhelper(this);
        find();
    }

    private void find() {
        recover = findViewById(R.id.buttonRecover);
        mobile = findViewById(R.id.RecoverMobile);
        password = findViewById(R.id.RecoverPassword);
        return_to_login = findViewById(R.id.turn_to_login_button);

        return_to_login.setOnClickListener(this);
        recover.setOnClickListener(this);
    }


    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonRecover) {
            String s1 = mobile.getText().toString().trim();
            String s2 = password.getText().toString().trim();
            boolean flag = myDBhelper.recoverbymobile(s1, s2);
            if (flag) {
                Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "修改失败！", Toast.LENGTH_SHORT).show();
            }
        }else if(id==R.id.turn_to_login_button){
            Intent i1=new Intent(this,com.example.chat.LoginActivity.class);
            startActivity(i1);
        }
    }
}