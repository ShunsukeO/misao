package com.example.sun.employeeoperatorphp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText eName, ePhone, eSalary;
    Button bBirthday;

    private int mYear, mMonth, mDay;
    static final int DATE_DIALOG_ID = 0;

    String name, birthday, phone, salary;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eName = (EditText) findViewById(R.id.name);
        ePhone = (EditText) findViewById(R.id.phone);
        eSalary = (EditText) findViewById(R.id.salary);

        bBirthday = (Button) findViewById(R.id.birthday);
        bBirthday.setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.all).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);

        helper = new DatabaseHelper(Register.this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.birthday: {
                showDialog(DATE_DIALOG_ID);
//                bBirthday.setTextColor(Color.parseColor("#ff0000"));
                bBirthday.setTextColor(Color.BLUE);
            }
            break;
            case R.id.register: {
                name = eName.getText().toString();
                birthday = mYear + "/" + mMonth + "/" + mDay;
                phone = ePhone.getText().toString();
                salary = eSalary.getText().toString();

                String method = "REGISTER";
                helper.execute(method, name, birthday, phone, salary);

                i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
            break;
            case R.id.all: {
                i = new Intent(Register.this, All.class);
                startActivity(i);
            }
            break;
            case R.id.home: {
                i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
            break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID: {
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            }
        }
        return null;
    }

    private void updateDisplay() {
        bBirthday.setText(new StringBuilder()
                .append(mYear).append("/")
                .append(mMonth + 1).append("/")
                .append(mDay).append("")
        );
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };
}
