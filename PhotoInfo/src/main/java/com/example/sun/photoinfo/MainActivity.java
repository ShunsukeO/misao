package com.example.sun.photoinfo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tTitle;
    EditText eName, ePhone;
    Button bBirthday, bTake, bRegister, bSearch;

    DatabaseHelper helper;

    private int mYear, mMonth, mDay;
    static final int DATE_DIALOG_ID = 0;

    static final int CAM_REQUEST = 1;
    int b = 1;
    public String fileName = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText) findViewById(R.id.eName);
        ePhone = (EditText) findViewById(R.id.ePhone);

        tTitle = (TextView) findViewById(R.id.title);
        tTitle.setOnClickListener(this);

        bBirthday = (Button) findViewById(R.id.birthday);
        bBirthday.setOnClickListener(this);

        bTake = (Button) findViewById(R.id.take);
        bTake.setOnClickListener(this);

        bRegister = (Button) findViewById(R.id.register);
        bRegister.setOnClickListener(this);

        bSearch = (Button) findViewById(R.id.search);
        bSearch.setOnClickListener(this);

        helper = new DatabaseHelper(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title: {
                Intent i = new Intent(MainActivity.this, All.class);
                startActivity(i);
            }
            break;
            case R.id.birthday: {
                showDialog(DATE_DIALOG_ID);
//                bBirthday.setTextColor(Color.parseColor("#ff0000"));
                bBirthday.setTextColor(Color.BLUE);
            }
            break;
            case R.id.take: {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);

                if(!fileName.contentEquals("0")){
                    bTake.setTextColor(Color.BLUE);
                }
            }
            break;
            case R.id.register: {

                String name = eName.getText().toString();
                String birthday = mYear + "/" + mMonth + "/" + mDay;
                String phone = ePhone.getText().toString();

                if(name.contentEquals("") || birthday.contentEquals("//") || phone.contentEquals("") || fileName.contentEquals("0")){
                    Toast.makeText(getApplicationContext(), "Fill the form", Toast.LENGTH_SHORT).show();
                }else{
                    int s = helper.insert(name, birthday, phone, fileName);
                    if (s == 1) {
                        Toast.makeText(getApplicationContext(), birthday, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case R.id.search: {
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivity(i);
            }
            break;
        }
    }

    private File getFile() {
        File folder = new File("sdcard/shun_app/PhotoInfo");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File image_file;
        while (true) {
            image_file = new File(folder, b + "camera_image.jpg");
            if (image_file.exists()) {
                b++;
            } else {
                break;
            }
        }

        fileName = b + "camera_image.jpg";
        return image_file;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
