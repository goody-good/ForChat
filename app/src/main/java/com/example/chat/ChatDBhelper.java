package com.example.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public class ChatDBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lily.db";
    private static final int DATABASE_VERSION = 1;
    //表名常量
    private static final String TABLE_ACTIVITY_1 = "Lilymessage";
    private static final String TABLE_ACTIVITY_2 = "Jackmessage";
    private static final String TABLE_ACTIVITY_3 = "Tommessage";
    private static final String TABLE_ACTIVITY_4 = "Alanmessage";
    private static final String TABLE_ACTIVITY_5 = "Brianmessage";
    private static final String TABLE_ACTIVITY_6 = "Bowenmessage";
    private static final String TABLE_ACTIVITY_7 = "Frankmessage";



    //创建表的语句
    private static final String CREATE_TABLE_ACTIVITY_1 = "CREATE TABLE " + TABLE_ACTIVITY_1 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_2 = "CREATE TABLE " + TABLE_ACTIVITY_2 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_3 = "CREATE TABLE " + TABLE_ACTIVITY_3 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_4 = "CREATE TABLE " + TABLE_ACTIVITY_4 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_5 = "CREATE TABLE " + TABLE_ACTIVITY_5 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_6 = "CREATE TABLE " + TABLE_ACTIVITY_6 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";
    private static final String CREATE_TABLE_ACTIVITY_7 = "CREATE TABLE " + TABLE_ACTIVITY_7 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)";


    private SQLiteDatabase db;
    //创建数据库和表
    public ChatDBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_1);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_2);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_3);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_4);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_5);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_6);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY_7);
    }

    //对Lilymessage表的增删改查
    //添加数据
    public boolean insertData(String content,String tablename) {
        //保存插入内容
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", content);
        long i = db.insert(tablename, null, contentValues);
        return i > 0;
    }

    //删除数据
    public boolean deleteData(String deletedId,String tablename) {
        int i = db.delete(tablename, "id=?", new String[]{deletedId});
        return  i > 0;
    }
    //删除全部数据
    public boolean deleteAllData(String tablename){
        int i = db.delete(tablename,null,null);
        return i>0;
    }
    //修改数据,根据Id
//    public boolean updateData(String updateId, Sring updateContent) {
//        //将需要更新的存入contentValues
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("content", updateContent);
//        db.update("Lilymessage", contentValues, "id=?", new String[]{updateId});
//    }

    //查询数据,将查询内容用Lily对象属性进行存储，并将对象存入集合中
    public List<LilyMsg> query(String tablename) {
        List<LilyMsg> list = new ArrayList<>();//多态
        Cursor cursor = db.query(tablename, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            LilyMsg lilyMsg = new LilyMsg();
            lilyMsg.setId(String.valueOf(cursor.getInt(0)));
            lilyMsg.setContent(cursor.getString(1));
            list.add(lilyMsg);
        }
        cursor.close();
    return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
