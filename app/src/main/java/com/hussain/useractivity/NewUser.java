package com.hussain.useractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewUser extends AppCompatActivity {

    EditText name, password;
    DatabaseAdapter MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        MyDB = new DatabaseAdapter(this);
        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void Register(View view) {
        boolean isInserted = MyDB.insertData(name.getText().toString(), password.getText().toString());

        if (isInserted == true) {
            Toast.makeText(NewUser.this, "Data is Inserted", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(NewUser.this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(NewUser.this, "ERROR", Toast.LENGTH_LONG).show();
    }
}
