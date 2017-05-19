package com.example.sun.todayapp0519;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Sensor extends AppCompatActivity implements SensorEventListener{

    SensorManager sm;
    TextView tv, tBack;
    int shun = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tv = (TextView)findViewById(R.id.text);
        tBack = (TextView)findViewById(R.id.back);
        tBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sensor.this, MainActivity.class);
                startActivity(i);
            }
        });
        sm = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == android.hardware.Sensor.TYPE_ACCELEROMETER){
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];
            float asr = (x * x + y * y + z * z)/(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if(asr >= 2){
                tv.setText("" + shun);
                shun++;
            }
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }
}
