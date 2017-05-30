package com.example.sun.loginsystem;

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
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText eName, ePhone, ePassword;
    Button bBirthday, bRegister, bBack;

    private int mYear, mMonth, mDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eName = (EditText) findViewById(R.id.name);
        ePhone = (EditText) findViewById(R.id.phone);
        ePassword = (EditText) findViewById(R.id.password);

        bBirthday = (Button)findViewById(R.id.birthday);
        bBirthday.setOnClickListener(this);
        bRegister = (Button)findViewById(R.id.register);
        bRegister.setOnClickListener(this);
        bBack = (Button)findViewById(R.id.back);
        bBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday:{
                showDialog(DATE_DIALOG_ID);
//                bBirthday.setTextColor(Color.parseColor("#ff0000"));
                bBirthday.setTextColor(Color.BLUE);
            }break;
            case R.id.register:{
                String name = eName.getText().toString();
                String birthday = mYear + "/" + (mMonth + 1) + "/" + mDay;
                String phone = ePhone.getText().toString();
                String password = ePassword.getText().toString();

                if(name.contentEquals("") || birthday.contentEquals("") || phone.contentEquals("") || password.contentEquals("")){
                    Toast.makeText(Register.this, "Fill the form", Toast.LENGTH_SHORT).show();
                }else{
                    String method = "REGISTER";
                    DatabaseHelper helper = new DatabaseHelper(Register.this);
                    helper.execute(method, name, birthday, phone, password);
                }
            }break;
            case R.id.back:{
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }break;
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
