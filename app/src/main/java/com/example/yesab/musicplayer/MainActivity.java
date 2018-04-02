package com.example.yesab.musicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    int index = 0;
    private SeekBar volumeSeekBar;
    private AudioManager audioManager;
    private int i;

    final int[] resID = { R.raw.fenetres,R.raw.amsterdam};

    //  the rest of the album
    // ,R.raw.track01, R.raw.track02,R.raw.track03,R.raw.track04,R.raw.track05,R.raw.track06,R.raw.track07,R.raw.track_no08
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       for (i = 0; i < resID.length - 1; i++) {
            mediaPlayer = MediaPlayer.create(this, resID[i]);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();


        Button buttonPlay = (Button) findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();

                if (index == 0) {
                    Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
                }
                if (index != 0) {
                    Toast.makeText(MainActivity.this, "resume", Toast.LENGTH_SHORT).show();
                    index = 0;
                }
                index++;

            }
        });
        Button buttonResume = (Button) findViewById(R.id.resume);
        buttonResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "next", Toast.LENGTH_SHORT).show();

            }
        });}
    }



    private void initControls() {
        try {
            volumeSeekBar = findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        } catch (Exception e) {
        }
    }
}

