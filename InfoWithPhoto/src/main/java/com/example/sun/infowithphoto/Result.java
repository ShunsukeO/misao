package com.example.sun.infowithphoto;

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

    ImageView imageView;
    TextView textView;
    Button button;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.info);
        button = (Button) findViewById(R.id.back);
        button.setOnClickListener(this);
        helper = new DatabaseHelper(Result.this);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("NAME");
        Cursor c = helper.show(name);
        c.moveToFirst();
        String phone = c.getString(c.getColumnIndex("Phone"));
        String image = c.getString(c.getColumnIndex("Image"));

        String path = "sdcard/shun_app/Info/" + image;
        imageView.setImageDrawable(Drawable.createFromPath(path));

        textView.setText("Name: " + name + "\nPhone: " + phone + "\nImage: " + image);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
    }
}
