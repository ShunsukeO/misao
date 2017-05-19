package com.example.sun.photoinfo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    ImageView iImage;
    TextView tInfo;
    Button bBack;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        iImage = (ImageView) findViewById(R.id.image);
        tInfo = (TextView) findViewById(R.id.info);
        bBack = (Button) findViewById(R.id.back);
        bBack.setOnClickListener(this);

        helper = new DatabaseHelper(Result.this);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("NAME");
        Cursor c = helper.show(name);
        c.moveToFirst();

        String birthday = c.getString(c.getColumnIndex("Birthday"));
        String phone = c.getString(c.getColumnIndex("Phone"));
        String fileName = c.getString(c.getColumnIndex("Filename"));

        String path = "sdcard/shun_app/PhotoInfo/" + fileName;
        iImage.setImageDrawable(Drawable.createFromPath(path));
        tInfo.setText("Name: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Result.this, Search.class);
        startActivity(i);
    }
}
