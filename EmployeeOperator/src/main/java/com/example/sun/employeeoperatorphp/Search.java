package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Search extends AppCompatActivity implements View.OnClickListener {

    EditText eId;
    TextView tShow;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        eId = (EditText) findViewById(R.id.id);
        tShow = (TextView) findViewById(R.id.show);

        findViewById(R.id.search).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search: {
                String id = eId.getText().toString();

                if (id.contentEquals("")) {
                    Toast.makeText(Search.this, "Enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                String method = "SEARCH";
                helper = new DatabaseHelper(Search.this);

                String[] attrs = {"ID", "Name", "Birthday", "Phone", "Salary"};
                String result = "";

                try {
                    result = helper.execute(method, id).get();
                    JSONObject jObj = new JSONObject(result);
                    JSONArray jArr = jObj.getJSONArray("employee");

                    result = "";
                    for (int i = 0; i < jArr.length(); i++) {
                        JSONObject temp = jArr.getJSONObject(i);
                        for (int j = 0; j < attrs.length; j++) {
                            result += attrs[j] + " : " + temp.getString(attrs[j]) + "\n";
                        }
                        result += "\n";
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tShow.setText(result);
            }
            break;
            case R.id.home: {
                Intent i = new Intent(Search.this, MainActivity.class);
                startActivity(i);
            }
            break;
        }
    }
}
