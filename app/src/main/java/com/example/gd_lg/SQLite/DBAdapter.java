package com.example.gd_lg.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAdapter{
    SQLiteDatabase db;
    Context context;

    public DBAdapter(Context context){
        this.context = context;
    }

    public class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String loginSQL = "create table login_info(account text, password text)";
            db.execSQL(loginSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    // 启动SQLite
    public void open(){
        DBOpenHelper openHelper = new DBOpenHelper(context, "UserDB", null, 1);
        try{
            db = openHelper.getWritableDatabase();
        }catch (Exception e){
            db = openHelper.getReadableDatabase();
        }
    }

    // 关闭SQLite
    public void close(){
        if (db != null){
            db.close();
            db = null;
        }
    }

    // 注册添加
    public boolean insert(String account, String password){
        boolean result = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", account);
        contentValues.put("password", password);
        if (db.insert("login_info", null, contentValues) > 0){
            result = true;
        }
        return result;
    }

    // 登录判断
    public boolean login(String account, String password){
        boolean result = false;
        Cursor cursor = db.query("login_info", new String[]{"account", "password"}, "account='"+account+"'and password='"+password+"'", null, null, null, null);
        if (cursor.getCount() == 1){
            result = true;
        }
        return result;
    }
}
