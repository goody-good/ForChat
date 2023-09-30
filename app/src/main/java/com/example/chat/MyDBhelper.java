package com.example.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chat.javabean.User;

public class MyDBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MYsqlite.db";
    private static final String create_users="create table users(mobile varchar(11),password varchar(32))";
    public MyDBhelper(@Nullable Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long register(User user){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mobile",user.getMobile());
        cv.put("password",user.getPassword());
        long users = db.insert("users",null,cv);
        return users;
    }
    public boolean login(String mobile,String password){
        SQLiteDatabase db1=getWritableDatabase();
        boolean result =false;
        Cursor users= db1.query("users",null,"mobile=?",new String[]{mobile},null,null,null);

        if(users!=null){
            while(users.moveToNext()){
                String password1 = users.getString(1);
                result=password1.equals(password);
                return result;
            }
        }
        return result;
    }
}
