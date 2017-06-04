package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Show extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView tResult;

    String[] orderBy = {"ID asc", "ID desc", "Salary asc", "Salary desc"};
    String[] showNumber = {"All", "5", "10", "20", "30", "50", "100"};

    Spinner sOrderBy, sShowNumber;
    String selectedOrder, selectedNumber;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tResult = (TextView) findViewById(R.id.result);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);

        sOrderBy = (Spinner) findViewById(R.id.orderBy);
        sShowNumber = (Spinner) findViewById(R.id.showNumber);

        ArrayAdapter<?> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orderBy);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sOrderBy.setAdapter(adapter1);
        sOrderBy.setOnItemSelectedListener(this);

        ArrayAdapter<?> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, showNumber);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sShowNumber.setAdapter(adapter2);
        sShowNumber.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show: {
                String method = "SHOW";
                helper = new DatabaseHelper(Show.this);

                String[] attrs = {"ID", "Name", "Birthday", "Phone", "Salary"};
                String result = "";

                try {
                    result = helper.execute(method, selectedOrder, selectedNumber).get();
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

                tResult.setText(result);
            }
            break;
            case R.id.home: {
                Intent i = new Intent(Show.this, MainActivity.class);
                startActivity(i);
            }
            break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        switch (spinner.getId()) {
            case R.id.orderBy: {
                selectedOrder = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), selectedOrder, Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.showNumber: {
                selectedNumber = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), selectedNumber, Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
