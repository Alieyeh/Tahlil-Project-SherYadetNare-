package com.example.alieyeh.appy;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.example.alieyeh.appy.LyricsJumbleActivity.genre;

/**
 * Created by nazanin-sarrafzadeh on 5/28/2018.
 */
public class MusicManager {

    public MediaPlayer song;
    private Runnable runnable;
    private Handler handler = new Handler();
    private SeekBar seekBar;
    public static int randomRow;
    private int pause;

    public MusicManager() {

    }

    public MusicManager(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    View.OnClickListener play = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!song.isPlaying()) {
                song.seekTo(pause);
                song.start();
                seekBar();
            }
        }
    };

    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            song.seekTo(0);
            song.start();
            seekBar();
        }
    };

    View.OnClickListener stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (song != null) {
                song.pause();
                pause = song.getCurrentPosition();
            }
        }
    };

    public void checkForFreeze() {
        if (song != null) {
            song.pause();
        }
    }

    public void checkForDestroy() {
        if (song != null) {
            song.release();
            handler.removeCallbacks(runnable);
        }
    }

    public void generateRandomId(int genre) {
        Random randomId = new Random();
        int bound=7;
        switch (genre){
            case 1:
                bound=FileManager.khazCount;
                break;
            case 2:
                bound=FileManager.popCount;

                break;
            case 3:
                bound=FileManager.sonatiCount;

                break;
            case 4:
                bound=FileManager.titrajCount;

                break;
        }
        randomRow = randomId.nextInt(bound) + 1;
    }

    public void playTheMusic(Context context) {
        File file = null;
        FileOutputStream fos;
        GameDbHelper givebytesounds = new GameDbHelper(context);
        try {
            file = File.createTempFile("sound", "sound");
            fos = new FileOutputStream(file);
            fos.write(givebytesounds.giveTheSong(randomRow,genre));
            fos.close();
            Log.d("File", file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        song = MediaPlayer.create(context, Uri.fromFile(file));
        song.start();
        seekBar();

    }
    public void Stop() {
        if (song != null) {
            song.stop();
            song.reset();
            song.release();
            song = null;
        }
    }
     void playMusic(Context context) {
        File file = null;
        FileOutputStream fos;
        generateRandomId(genre);
        GameDbHelper givebytesounds = new GameDbHelper(context);
        try {
            file = File.createTempFile("sound", "sound");
            fos = new FileOutputStream(file);
            fos.write(givebytesounds.giveTheSong(randomRow,genre));
            fos.close();
            Log.d("File", file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        song = MediaPlayer.create(context, Uri.fromFile(file));
        song.start();


    }
    private void seekBar() {
        seekBar.setMax(song.getDuration());
        playCycle();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    song.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void playCycle() {
        seekBar.setProgress(song.getCurrentPosition());
        if (song.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {

                    playCycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }



}
