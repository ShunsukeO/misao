package com.example.sun.infowithphoto;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText) findViewById(R.id.eName);
        button1 = (Button) findViewById(R.id.search);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.back);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search: {
                Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                String name = editText.getText().toString();
                Intent i = new Intent(Search.this, Result.class);
                i.putExtra("NAME", name);
                startActivity(i);
            }
            break;
            case R.id.back: {
                Intent intent = new Intent(Search.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
