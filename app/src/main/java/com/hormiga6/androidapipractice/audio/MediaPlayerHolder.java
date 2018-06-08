package com.hormiga6.androidapipractice.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MediaPlayerHolder implements PlayerAdapter {
    public static final int PLAYBACK_POSITION_REFREASH_INTERVAL_MS = 1000;
    private final Context mContext;
    private MediaPlayer mMediaPlayer;
    private int mResourceId;
    private PlaybackInfoListener mPlaybackInfoListener;
    private ScheduledExecutorService mExecutor;
    private Runnable mSeekbarPositionUpdateTask;

    public MediaPlayerHolder(Context context) {
        mContext = context.getApplicationContext();
    }

    private void initializeMediaPlayer(){
        if(mMediaPlayer == null){
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnCompletionListener(mp -> {
                stopUpdatingCallbackWithPosition(true);
                if(mPlaybackInfoListener != null){
                    mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.COMPLETED);
                    mPlaybackInfoListener.onPlaybackCompleted();
                }
            });
        }
    }

    public void setPlaybackInfoListener(PlaybackInfoListener listener){
        mPlaybackInfoListener = listener;
    }

    private void stopUpdatingCallbackWithPosition(boolean resetUIPlaybackPosition){
        if(mExecutor != null){
            mExecutor.shutdownNow();
            mExecutor = null;
            mSeekbarPositionUpdateTask = null;
            if(resetUIPlaybackPosition && mPlaybackInfoListener != null){
                mPlaybackInfoListener.onDurationChanged(0);
            }
        }
    }

    private void startUpdatingCallbackWithPosition(){
        if(mExecutor == null){
            mExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        if (mSeekbarPositionUpdateTask == null){
            mSeekbarPositionUpdateTask = () -> {
                updateProgressCallbavkTask();
            };
        }
        mExecutor.scheduleAtFixedRate(mSeekbarPositionUpdateTask, 0, PLAYBACK_POSITION_REFREASH_INTERVAL_MS,
                TimeUnit.MILLISECONDS);
    }

    private void updateProgressCallbavkTask(){
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
            int currentPosition = mMediaPlayer.getCurrentPosition();
            if(mPlaybackInfoListener != null){
                mPlaybackInfoListener.onPositionChanged(currentPosition);
            }
        }
    }

    @Override
    public void loadMedia(int resourceId) {
        mResourceId = resourceId;
        initializeMediaPlayer();

        AssetFileDescriptor assetFileDescriptor = mContext.getResources().openRawResourceFd(
                mResourceId);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mMediaPlayer.setDataSource(assetFileDescriptor);
            }else{
                mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor());
            }
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeProgressCallback();
    }

    @Override
    public void release() {
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public boolean isPlaying() {
        if(mMediaPlayer != null){
            return mMediaPlayer.isPlaying();
        }
        return false;
    }

    @Override
    public void play() {
        if(mMediaPlayer != null && !mMediaPlayer.isPlaying()){
            mMediaPlayer.start();
            if(mPlaybackInfoListener != null){
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PLAYING);
            }
            startUpdatingCallbackWithPosition();
        }

    }

    @Override
    public void reset() {
        if(mMediaPlayer != null){
            mMediaPlayer.reset();
            loadMedia(mResourceId);
            if(mPlaybackInfoListener != null){
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.RESET);
            }
            stopUpdatingCallbackWithPosition(true);
        }
    }

    @Override
    public void pause() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
            if(mPlaybackInfoListener != null){
                mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PAUSED);
            }
        }
    }

    @Override
    public void initializeProgressCallback() {
        final int duration = mMediaPlayer.getDuration();
        if(mPlaybackInfoListener != null){
            mPlaybackInfoListener.onDurationChanged(duration);
            mPlaybackInfoListener.onPositionChanged(0);
        }

    }

    @Override
    public void seekTo(int position) {

    }

    @Override
    public void setVolume(float volume) {
        if(mMediaPlayer != null){
            mMediaPlayer.setVolume(volume, volume);
        }
    }
}
