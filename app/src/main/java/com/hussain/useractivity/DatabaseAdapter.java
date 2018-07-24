package com.hussain.useractivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseAdapter extends SQLiteOpenHelper {

    public static final String DatabaseName = "User.db";
    public static final String TableName = "Userinfo";
    public static final String Col1 = "ID";
    public static final String Col2 = "NAME";
    public static final String Col3 = "PASSWORD";
    public static final String Col4 = "LoginTime";
    public static final String Col5 = "LastLogin";

    String id;

    public DatabaseAdapter(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TableName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PASSWORD TEXT, LoginTime INT, LastLogin TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,password);
        contentValues.put(Col4,0);
        contentValues.put(Col5,DateTime());

        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userinfo where ID = '"+ id +"'",null);
        return cursor;
    }

    public String user(String name, String password){

        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> loginTime = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from Userinfo where NAME = '"+ name +"' and PASSWORD = '"+ password +"'" ,null);

        int isExit = cursor.getCount();

        if(cursor.moveToFirst()) {
            do {
                ID.add(cursor.getString(cursor.getColumnIndex("ID")));
                loginTime.add(cursor.getString(cursor.getColumnIndex("LoginTime")));
            }while(cursor.moveToNext());
        }

        id = ID.get(0);
        String noOfLogin = loginTime.get(0);

        if (isExit>0){
            updateData(id, Integer.parseInt(noOfLogin));
            return id;
        }
        return "0";
    }

    public String DateTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
        return formattedDate;
    }

    public boolean updateData(String id, int LoginTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col4,LoginTime+1);
        contentValues.put(Col5,DateTime());

        sqLiteDatabase.update(TableName, contentValues, "ID = ?", new String[]{id});
        return true;
    }
}
