package com.example.sun.video;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button button;
    static final int CAM_REQUEST = 1;
    int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.video);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                File file = getfile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
    }

    private File getfile() {
        File folder = new File("sdcard/shun_app");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File video_file = new File(folder, b + "camera_video.mp4");
        return video_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/shun_app/" + b + "camera_video.mp4";
        videoView.setVideoPath(path);
        videoView.start();
        b++;
    }
}
