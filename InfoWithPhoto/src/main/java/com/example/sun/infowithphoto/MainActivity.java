package com.example.sun.infowithphoto;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText1, editText2;
    Button button1, button2, button3;
    static final int CAM_REQUEST = 1;
    int b = 1;
    public String fileName;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.eName);
        editText2 = (EditText) findViewById(R.id.ePhone);
        button1 = (Button) findViewById(R.id.take);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.resister);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.search);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take: {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
            break;
            case R.id.resister: {
                String name = editText1.getText().toString();
                String phone = editText2.getText().toString();
                helper = new DatabaseHelper(MainActivity.this);
                int s = helper.insert(name, phone, fileName);
                if (s == 1) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }

            }
            break;
            case R.id.search: {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
            break;
        }
    }

    private File getFile() {
        File folder = new File("sdcard/shun_app/Info");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File image_file = new File(folder, b + "camera_image.jpg");
        fileName = b + "camera_image.jpg";
        b++;
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        b++;
    }
}
