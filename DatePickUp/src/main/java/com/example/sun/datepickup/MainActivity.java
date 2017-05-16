package com.example.sun.datepickup;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    TextView textView;
    String Year, Month, Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        textView = (TextView)findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Year = year + "";
                Month = monthOfYear + "";
                Day = dayOfMonth + "";

            }
        }, 2017, 5, 16);
        datePickerDialog.show();

        textView.setText("" + Year + " " + Month + " " + Day);
    }
}


