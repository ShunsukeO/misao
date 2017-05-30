package com.example.sun.loginsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eName, ePassword;
    Button bLogin;
    TextView tRegister;
//    String et_name, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText)findViewById(R.id.name);
        ePassword = (EditText)findViewById(R.id.password);
        bLogin = (Button)findViewById(R.id.login);
        bLogin.setOnClickListener(this);
        tRegister = (TextView) findViewById(R.id.register);
        tRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:{
                String name = eName.getText().toString();
                String password = ePassword.getText().toString();

                if(name.contentEquals("") || password.contentEquals("")){
                    Toast.makeText(MainActivity.this, "Name or Password is blank", Toast.LENGTH_SHORT).show();
                }else {
                    String method = "LOGIN";
                    DatabaseHelper helper = new DatabaseHelper(this);
                    helper.execute(method, name, password);
                }
            }break;
            case R.id.register:{
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }break;
        }
    }
}
