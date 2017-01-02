package com.example.admin.database_listviewex1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 1/2/2017.
 */
public class MyDatabase {
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    //consturucter add
    public  MyDatabase (Context c){
        myHelper=new MyHelper(c,"techpalle.db",null,1);
    }
    public void open(){
        sqLiteDatabase=myHelper.getWritableDatabase();
    }
    public void insertStudent(String snmae,String sub){
        ContentValues contentValues=new ContentValues();
        contentValues.put("sname",snmae);
        contentValues.put("ssub",sub);
        sqLiteDatabase.insert("student",null,contentValues);

    }
    public Cursor queryStudent(){
        Cursor cursor=null;
        cursor=sqLiteDatabase.query("student",null,null,null,null,null,null);
        return cursor;
    }
    public  void close(){
        sqLiteDatabase.close();
    }

    public class MyHelper extends SQLiteOpenHelper{


        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table student(_id integer primary key,sname text,ssub text); ");


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
