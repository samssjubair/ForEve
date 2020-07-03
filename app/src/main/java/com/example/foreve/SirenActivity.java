package com.example.foreve;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SirenActivity extends AppCompatActivity implements View.OnClickListener {
    private Button stopSirenButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siren);
        stopSirenButton=(Button)findViewById(R.id.sirenOffId);

        mediaPlayer=MediaPlayer.create(this,R.raw.siren);
        stopSirenButton.setOnClickListener(this);
        mediaPlayer.start();


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sirenOffId)
        {
            mediaPlayer.stop();
        }
    }
}