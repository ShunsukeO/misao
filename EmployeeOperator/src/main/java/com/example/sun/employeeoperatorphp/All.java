package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class All extends AppCompatActivity {

    TextView tShow;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(All.this, MainActivity.class);
                startActivity(i);
            }
        });

        tShow = (TextView) findViewById(R.id.show);

        String method = "ALL";
        helper = new DatabaseHelper(All.this);

        String[] column = {"ID", "Name", "Birthday", "Phone", "Salary"};
        String result = "";

        try {
            result = helper.execute(method).get();
            JSONObject jObj = new JSONObject(result);
            JSONArray jArr = jObj.getJSONArray("all");

            result = "";
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject temp = jArr.getJSONObject(i);
                for (int j = 0; j < column.length; j++) {
                    result += column[j] + ": " + temp.getString(column[j]) + "\n";
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
}
