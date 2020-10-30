package com.example.ev02_cristianmolina.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ev02_cristianmolina.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class InformationActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        videoView = (VideoView)findViewById(R.id.vw);
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta);
        videoView.setVideoURI(uri);
        MediaController media = new MediaController(this);
        videoView.setMediaController(media);
    }

    /**
     * On click.
     *
     * @param view the view
     */
    public void onClick(View view) {
        Intent intent = new Intent(InformationActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}