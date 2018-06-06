package com.hormiga6.androidapipractice.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hormiga6.androidapipractice.R;

public class AudioFocusActivity extends AppCompatActivity {

    private static final String TAG =  AudioFocusActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_focus);

        requestAudoFocus();
    }

    //about audio focus https://medium.com/google-developers/audio-focus-1-6b32689e4380
    private void requestAudoFocus(){
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //for android N and earlier
        int focusRequest = audioManager.requestAudioFocus((int focusChange)-> {
            String state = "";
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    state = "GAIN";
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    state = "LOSS_TRANSIENT_CAN_DUCK";
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    state = "LOSS_TRANSIENT";
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    state = "LOSS";
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
}
