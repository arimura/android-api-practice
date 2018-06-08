package com.hormiga6.androidapipractice.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.hormiga6.androidapipractice.R;

import java.io.IOException;
import java.io.InputStream;

public class AudioFocusActivity extends AppCompatActivity {
    private static final String TAG =  AudioFocusActivity.class.getSimpleName();
    private static final int MEDIA_RES_ID = R.raw.sound;
    private SeekBar mSeekBarAudio;
    private PlayerAdapter mPlayerAdapter;
    private boolean mUserIsSeeking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_focus);
        initializeUI();
        initializeSeekbar();
        initializePlaybackController();
        requestAudoFocus();
        mPlayerAdapter.loadMedia(MEDIA_RES_ID);
    }

    private void initializeUI(){
        Button mPlayButton = findViewById(R.id.button_play);
        Button mPauseButton = findViewById(R.id.button_pause);
        Button mResetButton = findViewById(R.id.button_reset);
        mSeekBarAudio = findViewById(R.id.seekbar_audio);

        mPauseButton.setOnClickListener(v -> {
            mPlayerAdapter.pause();
        });
        mPlayButton.setOnClickListener(v -> {
            mPlayerAdapter.play();
        });
        mResetButton.setOnClickListener(v -> {
            mPlayerAdapter.reset();
        });
    }

    private void initializePlaybackController(){
        MediaPlayerHolder mediaPlayerHolder = new MediaPlayerHolder(this);
        mediaPlayerHolder.setPlaybackInfoListener(new PlaybackListner());
        mPlayerAdapter = mediaPlayerHolder;
    }

    private void initializeSeekbar(){
        mSeekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int userSelectPosition = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    userSelectPosition = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                    mUserIsSeeking = false;
                    mPlayerAdapter.seekTo(userSelectPosition);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mUserIsSeeking = true;
            }
        });
    }

    //about audio focus https://medium.com/google-developers/audio-focus-1-6b32689e4380
    private void requestAudoFocus(){
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //for android N and earlier
        int focusRequest = audioManager.requestAudioFocus(focusChange -> {
            String state = "";
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    state = "GAIN";
                    if(mPlayerAdapter != null){
                        mPlayerAdapter.setVolume(1f);
                    }
                    if(!mPlayerAdapter.isPlaying()){
                        mPlayerAdapter.play();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    state = "LOSS_TRANSIENT_CAN_DUCK";
                    if(mPlayerAdapter != null && mPlayerAdapter.isPlaying()){
                        mPlayerAdapter.setVolume(0.5f);
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    state = "LOSS_TRANSIENT";
                    if(mPlayerAdapter != null && mPlayerAdapter.isPlaying()){
                        mPlayerAdapter.pause();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    state = "LOSS";
                    break;
                default:
                    state = "unknown";
            }
            Log.d(TAG, "requestAudoFocus: " + state);

        }, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        switch (focusRequest) {
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                Log.d(TAG, "audio focus request failed");
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                Log.d(TAG, "audio focus request granted");
                break;
        }
    }

    class PlaybackListner extends PlaybackInfoListener {
        @Override
        public void onDurationChanged(int duration){
            mSeekBarAudio.setMax(duration);
        }

        @Override
        public void onPositionChanged(int position){
            if(!mUserIsSeeking){
                mSeekBarAudio.setProgress(position);
            }
        }

        @Override
        public void onStateChanged(@State int state){
            String stateToString = PlaybackInfoListener.convertStateToString(state);
            Log.d(TAG, "onStateChanged: " + stateToString);
        }
    }
}
