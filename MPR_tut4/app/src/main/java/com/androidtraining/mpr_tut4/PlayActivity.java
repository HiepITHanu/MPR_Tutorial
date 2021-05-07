package com.androidtraining.mpr_tut4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);

        Intent intent = getIntent();
        String link = intent.getStringExtra("LINK");
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(link);

        videoView.setMediaController(new MediaController(this));
        //https://download.ted.com/talks/ElonMusk_2017-480p.mp4
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        position = videoView.getCurrentPosition();
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt("PLAYBACK", this.position);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        videoView.seekTo(savedInstanceState.getInt("PLAYBACK"));
    }
}
