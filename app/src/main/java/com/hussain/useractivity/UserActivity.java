package com.hussain.useractivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class UserActivity extends AppCompatActivity {

    TextView name, NoOfLogin, LastTimeLogin;
    DatabaseAdapter MyDB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        MyDB = new DatabaseAdapter(this);
        name = findViewById(R.id.name);
        NoOfLogin = findViewById(R.id.noOfLogin);
        LastTimeLogin = findViewById(R.id.LastLogin);
        String value = getIntent().getExtras().getString("ID");
        int id = Integer.parseInt(value);

        sqLiteDatabase = MyDB.getReadableDatabase();
        Cursor cursor = MyDB.getData(id);
        if (cursor.moveToFirst()){
            name.setText(cursor.getString(1));
            NoOfLogin.setText(cursor.getString(3));
            LastTimeLogin.setText(cursor.getString(4));
        }
    }
}
