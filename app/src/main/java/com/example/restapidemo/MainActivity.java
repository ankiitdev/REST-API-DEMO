package com.example.restapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_get(View view){
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        startActivity(intent);
    }

    public void btn_post(View view){
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        startActivity(intent);
    }

    public void btn_put(View view){
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        startActivity(intent);
    }

    public void btn_token(View view){
        Intent intent = new Intent(MainActivity.this,TokenActivity.class);
        startActivity(intent);
    }
}
