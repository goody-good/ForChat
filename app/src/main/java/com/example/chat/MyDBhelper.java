package com.example.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chat.javabean.User;

public class MyDBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MYsqlite.db";
    private static final int DATABASE_VERSION = 1;
    private static final String FRIEND_ID = "FriendId";
    private static final String CHAT_CONTENT = "Content";
    private static final String create_users="create table users(mobile varchar(11),password varchar(32))";
    public MyDBhelper(@Nullable Context context){
        super(context,DB_NAME,null,DATABASE_VERSION);
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
//    public void addMessage(myMessage myMessage){
//        SQLiteDatabase db2=getWritableDatabase();
//        ContentValues values=new ContentValues();
//        values.put("FriendId", myMessage.getFriendId());
//        values.put("Content", myMessage.getContent());
//        db2.insert(TABLE_MESSAGES,null,values);
//        db2.close();
//    }
//    public List<myMessage> getMessagesByFriendId(String friendId) {
//        List<myMessage> myMessages = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_MESSAGES +
//                " WHERE " + "FriendId" + " = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{friendId});
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex("FriendId"));
//                String content = cursor.getString(cursor.getColumnIndex("Content"));
//                myMessage myMessage = new myMessage(friendId, content);
//                myMessages.add(myMessage);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return myMessages;
//    }
}
