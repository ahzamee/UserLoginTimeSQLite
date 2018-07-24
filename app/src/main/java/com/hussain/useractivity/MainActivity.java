package com.hussain.useractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, password;
    DatabaseAdapter MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDB = new DatabaseAdapter(this);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
    }

    public void Login(View view) {
        try {
            String status = MyDB.user(name.getText().toString(),password.getText().toString());
            int id = Integer.parseInt(status);
            if(id>0) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("ID",status);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(),"User Does Not EXIT",Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Wrong Information", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registration(View view) {
        Intent intent = new Intent(MainActivity.this, NewUser.class);
        startActivity(intent);
    }
}
