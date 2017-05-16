package com.example.sun.batterystatuswithbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {

    TextView textView;
    ProgressBar progressBar;

    private BroadcastReceiver mbcr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            textView = (TextView) findViewById(R.id.text);
            progressBar = (ProgressBar) findViewById(R.id.progress);
            progressBar.setProgress(level);
            textView.setText("Batterylevel:" + Integer.toString(level) + "%");
            if (level == 100) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd("small.mp4");
                    MediaPlayer mp = new MediaPlayer();
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(mbcr, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
