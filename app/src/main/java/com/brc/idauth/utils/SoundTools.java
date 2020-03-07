package com.brc.idauth.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-15
       Time     :  18:13
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SoundTools {


    /**
     * 播放系统拍照声音
     */
    public static void playVerifySuccessSound(Context context) {
        MediaPlayer mediaPlayer = null;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int volume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

        if (volume != 0) {
            if (mediaPlayer == null)
                mediaPlayer = MediaPlayer.create(context, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }
    }

    /**
     * 播放系统拍照声音
     */
    public static void playVerifyFailSound(Context context) {
        MediaPlayer mediaPlayer = null;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int volume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

        if (volume != 0) {
            if (mediaPlayer == null)
                mediaPlayer = MediaPlayer.create(context, Uri.parse("file:///system/media/audio/ui/LowBattery.ogg"));
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }
    }
}
