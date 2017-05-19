package com.example.sun.photoinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class All extends AppCompatActivity implements View.OnClickListener {

    TextView tInfo;
    Button bBack;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        tInfo = (TextView) findViewById(R.id.info);
        bBack = (Button) findViewById(R.id.back);
        bBack.setOnClickListener(this);
        helper = new DatabaseHelper(All.this);

        Cursor c = helper.showall();
        c.moveToFirst();
        do {
            String name = c.getString(c.getColumnIndex("Name"));
            String birthday = c.getString(c.getColumnIndex("Birthday"));
            String phone = c.getString(c.getColumnIndex("Phone"));
            String fileName = c.getString(c.getColumnIndex("Filename"));
            tInfo.append("\n\nName: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone + "\nFilename: " + fileName);
        } while (c.moveToNext());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(All.this, MainActivity.class);
        startActivity(i);
    }
}
