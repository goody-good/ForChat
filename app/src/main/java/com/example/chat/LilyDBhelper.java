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

public class LilyDBhelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //创建数据库和表
    public LilyDBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Lilymessage(id integer primary key autoincrement,content text)");
    }

    //对Lilymessage表的增删改查
    //添加数据
    public boolean insertData(String content) {
        //保存插入内容
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", content);
        long i = db.insert("Lilymessage", null, contentValues);
        return i > 0;
    }

    //删除数据
    public boolean deleteData(String deletedId) {
        int i = db.delete("Lilymessage", "id=?", new String[]{deletedId});
        return  i > 0;
    }

    //修改数据,根据Id
//    public boolean updateData(String updateId, Sring updateContent) {
//        //将需要更新的存入contentValues
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("content", updateContent);
//        db.update("Lilymessage", contentValues, "id=?", new String[]{updateId});
//    }

    //查询数据,将查询内容用Lily对象属性进行存储，并将对象存入集合中
    public List<LilyMsg> query() {
        List<LilyMsg> list = new ArrayList<>();//多态
        Cursor cursor = db.query("Lilymessage", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            LilyMsg lilyMsg = new LilyMsg();
            lilyMsg.setId(String.valueOf(cursor.getInt(0)));
            lilyMsg.setContent(cursor.getString(1));
            list.add(lilyMsg);
        }
    return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
