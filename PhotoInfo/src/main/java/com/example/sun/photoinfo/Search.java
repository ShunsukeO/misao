package com.example.sun.photoinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity implements View.OnClickListener {

    EditText eName;
    Button bSearch, bBack;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        eName = (EditText) findViewById(R.id.eName);

        bSearch = (Button) findViewById(R.id.search);
        bSearch.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.back);
        bBack.setOnClickListener(this);

        helper = new DatabaseHelper(Search.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search: {
                String name = eName.getText().toString();
                Cursor c = helper.showall();
                c.moveToFirst();
                int flag = 0;
                do {
                    String cName = c.getString(c.getColumnIndex("Name"));
                    if (cName.contentEquals(name)) {
                        flag++;
                    }
                } while (c.moveToNext());

                if (flag == 0) {
                    Toast.makeText(getApplicationContext(), "No name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(Search.this, Result.class);
                    i.putExtra("NAME", name);
                    startActivity(i);
                }
            }
            break;
            case R.id.back: {
                Intent i = new Intent(Search.this, MainActivity.class);
                startActivity(i);
            }
            break;
        }

    }
}
