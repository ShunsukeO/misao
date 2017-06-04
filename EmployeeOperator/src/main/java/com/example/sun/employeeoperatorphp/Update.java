package com.example.sun.employeeoperatorphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Update extends AppCompatActivity implements View.OnClickListener {

    LinearLayout LName, LBirthday, LPhone, LSalary;
    TextView tEnterUpdate;
    EditText eId, eName, ePhone, eBirthday, eSalary;
    Button bUpdate;

    String id = "";

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        LName = (LinearLayout) findViewById(R.id.nameSection);
        LBirthday = (LinearLayout) findViewById(R.id.birthdaySection);
        LPhone = (LinearLayout) findViewById(R.id.phoneSection);
        LSalary = (LinearLayout) findViewById(R.id.salarySection);

        tEnterUpdate = (TextView) findViewById(R.id.enterupdate);
        eId = (EditText) findViewById(R.id.id);
        eName = (EditText) findViewById(R.id.name);
        ePhone = (EditText) findViewById(R.id.phone);
        eBirthday = (EditText) findViewById(R.id.birthday);
        eSalary = (EditText) findViewById(R.id.salary);
        bUpdate = (Button) findViewById(R.id.update);

        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.all).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String method;

        switch (v.getId()) {
            case R.id.ok: {
                id = eId.getText().toString();
                String oriName = "";
                String oriBirthday = "";
                String oriPhone = "";
                String oriSalary = "";

                if (id.contentEquals("")) {
                    Toast.makeText(Update.this, "Enter an ID", Toast.LENGTH_SHORT).show();
                } else {
                    method = "CONFIRM";
                    helper = new DatabaseHelper(Update.this);
                    String result = "";
                    try {
                        result = helper.execute(method, id).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    if (result.equals("NO")) {
                        Toast.makeText(Update.this, "The ID is not found", Toast.LENGTH_SHORT).show();
                        tEnterUpdate.setVisibility(View.GONE);
                        LName.setVisibility(View.GONE);
                        LBirthday.setVisibility(View.GONE);
                        LPhone.setVisibility(View.GONE);
                        LSalary.setVisibility(View.GONE);
                        bUpdate.setVisibility(View.GONE);
                        return;
                    }

                    tEnterUpdate.setVisibility(View.VISIBLE);
                    LName.setVisibility(View.VISIBLE);
                    LBirthday.setVisibility(View.VISIBLE);
                    LPhone.setVisibility(View.VISIBLE);
                    LSalary.setVisibility(View.VISIBLE);
                    bUpdate.setVisibility(View.VISIBLE);

                    method = "SEARCH";

                    String[] attrs = {"ID", "Name", "Birthday", "Phone", "Salary"};
                    try {
                        helper = new DatabaseHelper(Update.this);
                        result = helper.execute(method, id).get();
                        JSONObject jObj = new JSONObject(result);
                        JSONArray jArr = jObj.getJSONArray("employee");


                        for (int i = 0; i < jArr.length(); i++) {
                            JSONObject temp = jArr.getJSONObject(i);

                            oriName = temp.getString(attrs[1]);
                            oriBirthday = temp.getString(attrs[2]);
                            oriPhone = temp.getString(attrs[3]);
                            oriSalary = temp.getString(attrs[4]);
                        }

                        eName.setHint(oriName);
                        eBirthday.setHint(oriBirthday);
                        ePhone.setHint(oriPhone);
                        eSalary.setHint(oriSalary);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case R.id.update: {
                method = "UPDATE";

                String name = eName.getText().toString();
                String birthday = eBirthday.getText().toString();
                String phone = ePhone.getText().toString();
                String salary = eSalary.getText().toString();

                helper = new DatabaseHelper(Update.this);
                helper.execute(method, id, name, birthday, phone, salary);

                intent = new Intent(Update.this, MainActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.all: {
                intent = new Intent(Update.this, All.class);
                startActivity(intent);
            }
            break;
            case R.id.home: {
                intent = new Intent(Update.this, MainActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
