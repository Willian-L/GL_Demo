package com.example.gd_lg.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gd_lg.encapsulation.SQLiteInfo;

public class SQLiteDBAdapter {
    SQLiteDatabase db;
    Context context;
    public String TABLE_NAME = "TABLE_NAME";

    public SQLiteDBAdapter(Context context){
        this.context = context;
    }

    public class DBOpenHelper extends SQLiteOpenHelper{

        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(stu_id TEXT, stu_name TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }

    public boolean open(){
        DBOpenHelper openHelper = new DBOpenHelper(context, "SQLiteDB", null, 1);
        try {
            db = openHelper.getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(stu_id TEXT, stu_name TEXT)");
            return true;
        }catch (Exception e){
            db = openHelper.getReadableDatabase();
            return false;
        }
    }

    public boolean close(){
        if (db != null){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.close();
            db = null;
            return true;
        }
        return false;
    }

    public String insert(String id, String name){
        String result = "Insert Failed!";
        ContentValues contentValues = new ContentValues();
        contentValues.put("stu_id", id);
        contentValues.put("stu_name", name);
        Cursor corsor_before = db.query(TABLE_NAME, new String[]{"stu_id"}, "stu_id='" + id + "'",null, null, null, null);
        if (corsor_before.getCount() <= 0){
            if (db.insert(TABLE_NAME, null, contentValues) > 0) {
                Cursor corsor_after = db.query(TABLE_NAME, new String[]{"stu_id", "stu_name"}, "stu_id='" + id + "'",null, null, null, null);
                while (corsor_after.moveToNext()){
                    String stu_id = corsor_after.getString(corsor_after.getColumnIndex("stu_id"));
                    String stu_name = corsor_after.getString(corsor_after.getColumnIndex("stu_name"));
                    result = "ID: " + stu_id + "\n"
                            + "Name: " + stu_name + "\n"
                            + "Insert Successfully!";
                }
            }
        } else {
            result = "ID:" + id + " already exists" + "\n"
                    + "Insert Failed!";
        }
        return result;
    }

    public String delete(String id){
        String result = "Delete Failed!";
        if (db.delete(TABLE_NAME, "stu_id=?",new String[]{id}) > 0){
            result = "Delete Successfully!";
        }
        return result;
    }

    public String query(String id){
        Cursor cursor = db.query(TABLE_NAME, new String[]{"stu_id", "stu_name"}, "stu_id='" + id + "'",null, null, null, null);
        String result = "ID:"+ id +" was not found";
        if (cursor.getCount() > 0){
            if (cursor.moveToNext()) {
                String stu_id = cursor.getString(cursor.getColumnIndex("stu_id"));
                String stu_name = cursor.getString(cursor.getColumnIndex("stu_name"));
                result = "ID: " + stu_id + "\n"
                        + "Name: " + stu_name;
            }
        }
        return result;
    }

    public String update(String old_id, String new_id) {
        Cursor cursor_old = db.query(TABLE_NAME, new String[]{"stu_id"}, "stu_id='" + old_id + "'", null, null, null, null);
        Cursor cursor_new = db.query(TABLE_NAME, new String[]{"stu_id"}, "stu_id='" + new_id + "'", null, null, null, null);
        ContentValues contentValues = new ContentValues();
        String result = "ID:"+ old_id +" was not found";
        if (cursor_old.getCount() > 0) {
            if (cursor_new.getCount() <= 0) {
                contentValues.put("stu_id", new_id);
                if (db.update(TABLE_NAME, contentValues, "stu_id='" + old_id + "'", null) > 0) {
                    result = "Old ID: " + old_id + "\n"
                            + "New ID: " + new_id + "\n"
                            + "Update Successfully!";
                }
            }else {
                result = "ID:" + new_id + " already exists" + "\n"
                        + "Update Failed!";
            }
        }
        return result;
    }
}
