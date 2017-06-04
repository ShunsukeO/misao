package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.all).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.search).setOnClickListener(this);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all: {
                Intent i = new Intent(MainActivity.this, All.class);
                startActivity(i);
            }
            break;
            case R.id.register: {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
            break;
            case R.id.update: {
                Intent i = new Intent(MainActivity.this, Update.class);
                startActivity(i);
            }
            break;
            case R.id.search: {
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivity(i);
            }
            break;
            case R.id.show: {
                Intent i = new Intent(MainActivity.this, Show.class);
                startActivity(i);
            }
            break;
            case R.id.delete: {
                Intent i = new Intent(MainActivity.this, Delete.class);
                startActivity(i);
            }
            break;
        }
    }
}
