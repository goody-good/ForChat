package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LilyActivity extends ChatActivity {

    public static final String FRIEND_ID="lily123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lily);
        setFirendId(FRIEND_ID);
    }

}