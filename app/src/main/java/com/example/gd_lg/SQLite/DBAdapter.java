package com.example.gd_lg.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gd_lg.encapsulation.LinearUserInfo;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    SQLiteDatabase db;
    Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS login_info (account text, password text)");
            db.execSQL("CREATE TABLE IF NOT EXISTS linear (account text, password text, sex varchar(6), remember varchar(6), autoLogin varchar(6))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS login_info");
            db.execSQL("DROP TABLE IF EXISTS linear");
        }
    }

    // 启动SQLite
    public void open() {
        DBOpenHelper openHelper = new DBOpenHelper(context, "UserDB", null, 1);
        try {
            db = openHelper.getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS linear (account text, password text, sex varchar(6), remember varchar(6), autoLogin varchar(6))");
        } catch (Exception e) {
            db = openHelper.getReadableDatabase();
        }
    }

    // 关闭SQLite
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    // LinearLayoutActivity注册添加
    public boolean insert_linear(String account, String password, String sex, String remember, String autoLogin) {
        boolean result = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", account);
        contentValues.put("password", password);
        contentValues.put("sex", sex);
        contentValues.put("remember", remember);
        contentValues.put("autoLogin", autoLogin);
        Cursor cursor = db.query("linear", new String[]{"account"}, "account='" + account + "'", null, null, null, null);
        if (cursor.getCount() <= 0) {
            if (db.insert("linear", null, contentValues) > 0) {
                result = true;
            }
        }
        return result;
    }

    // LinearLayoutActivity登录判断
    public boolean login_linear(String account, String password) {
        boolean result = false;
        Cursor cursor = db.query("linear", new String[]{"account", "password"}, "account='" + account + "'and password='" + password + "'", null, null, null, null);
        if (cursor.getCount() == 1) {
            result = true;
        }
        return result;
    }

    // LinearLayoutActivity查询
    public List<LinearUserInfo> select(String account, String password) {
        List<LinearUserInfo> list = new ArrayList<LinearUserInfo>();
        Cursor cursor = db.query("linear", new String[]{"account", "password", "sex", "remember", "autoLogin"}, "account='" + account + "'and password='" + password + "'", null, null, null, null);
        while (cursor.moveToNext()) {
            LinearUserInfo linearUserInfo = new LinearUserInfo();
            linearUserInfo.setAccount(cursor.getString(cursor.getColumnIndex("account")));
            linearUserInfo.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            linearUserInfo.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            linearUserInfo.setRemember(cursor.getString(cursor.getColumnIndex("remember")));
            linearUserInfo.setAutoLogin(cursor.getString(cursor.getColumnIndex("autoLogin")));
            list.add(linearUserInfo);
        }
        return list;
    }

    // 注册添加
    public boolean insert(String account, String password) {
        boolean result = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", account);
        contentValues.put("password", password);
        Cursor cursor = db.query("login_info", new String[]{"account"}, "account='" + account + "'", null, null, null, null);
        if (cursor.getCount() <= 0){
            if (db.insert("login_info", null, contentValues) > 0) {
                result = true;
            }
        }
        return result;
    }

    // 登录判断
    public boolean login(String account, String password) {
        boolean result = false;
        Cursor cursor = db.query("login_info", new String[]{"account", "password"}, "account='" + account + "'and password='" + password + "'", null, null, null, null);
        if (cursor.getCount() >= 1) {
            result = true;
        }
        return result;
    }
}
