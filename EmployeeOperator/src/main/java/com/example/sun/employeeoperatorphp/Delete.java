package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Delete extends AppCompatActivity implements View.OnClickListener {

    EditText eId;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        eId = (EditText) findViewById(R.id.id);

        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete: {
                String id = eId.getText().toString();

                if (id.contentEquals("")) {
                    Toast.makeText(Delete.this, "Enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                String method = "DELETE";
                String result = "";

                helper = new DatabaseHelper(Delete.this);
                try {
                    result = helper.execute(method, id).get();
                    Toast.makeText(Delete.this, result, Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (result.equals("Delete Success")){
                    Intent i = new Intent(Delete.this, MainActivity.class);
                    startActivity(i);
                }
            }
            break;
            case R.id.home: {
                Intent i = new Intent(Delete.this, MainActivity.class);
                startActivity(i);
            }
            break;
        }
    }
}
