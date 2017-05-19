package com.example.sun.todayapp0519;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    WifiManager wm;
    BluetoothAdapter ba = null;

    Button bWifi, bBluetooth, bSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bWifi = (Button)findViewById(R.id.wifi);
        bWifi.setOnClickListener(this);
        bBluetooth = (Button)findViewById(R.id.bluetooth);
        bBluetooth.setOnClickListener(this);
        bSensor = (Button)findViewById(R.id.sensor);
        bSensor.setOnClickListener(this);

        ba = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wifi:{
                wm = (WifiManager) getSystemService(WIFI_SERVICE);
                if (wm.isWifiEnabled()) {
                    bWifi.setText("Wifi On");
                    wm.setWifiEnabled(false);
                } else {
                    bWifi.setText("Wifi Off");
                    wm.setWifiEnabled(true);
                }
            }break;
            case R.id.bluetooth:{
                if (ba == null) {
                    Toast.makeText(getApplicationContext(), "No Bluetooth", Toast.LENGTH_SHORT).show();
                } else {
                    if (!ba.isEnabled()) {
                        ba.enable();
                        bBluetooth.setText("Bluetooth Off");
                    } else {
                        ba.disable();
                        bBluetooth.setText("Bluetooth On");
                    }
                }
            }break;
            case R.id.sensor:{
                Intent i = new Intent(MainActivity.this, Sensor.class);
                startActivity(i);
            }break;
        }
    }
}
