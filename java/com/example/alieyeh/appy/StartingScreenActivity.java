package com.example.alieyeh.appy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    private static final int REQUEST_CODE_QUIZ2 = 2;
    private static final int REQUEST_CODE_QUIZ3 = 3;
    private static final int REQUEST_CODE_ROUND = 10;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    public static final String SHARED_PREFS2 = "singerNameSharedPrefs";
    public static final String KEY_HIGHSCORE2 = "singerNameKeyHighscore";
    public static final String SHARED_PREFS3 = "songNameSharedPrefs";
    public static final String KEY_HIGHSCORE3 = "songNameKeyHighscore";
    public static final String SHARED_PREFS_C_HIGHSCORE = "sharedPrefsCHigh";
    public static final String KEY_C_HIGHSCORE = "keyCHighscore";

    public static final String SHARED_PREFS_COIN = "CoinsSharedPrefs";
    public static final String KEY_COIN = "CoinKey";

    public static int JumbleLyricHighscore;
    public static int singerNameHighScore;
    public static int songNameHighScore;
    public static long coin = 0;


    static Context context;

    private TextView textViewCoin;
    private TextView textViewSingerNameHighScore;
    private TextView lyricsJumble;
    private TextView textViewSongNameHighScore;
    //TextView quizSinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        context = this;
        Button start =(Button) findViewById(R.id.start);
        Button genre =(Button) findViewById(R.id.genree);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSingerName();
            }
        });
        lyricsJumble =(TextView) findViewById(R.id.lyricsJumbleScore);
        textViewSingerNameHighScore =(TextView) findViewById(R.id.singerNameScore);
        textViewSongNameHighScore =(TextView) findViewById(R.id.songNameScore);
        textViewCoin =(TextView) findViewById(R.id.coin);

        loadJumbleLyricHighScore();
        loadSingerNameHighScore();
        loadSongNameHighScore();
        loadCoins();
    }

    private void goToSingerName() {
        Intent intent = new Intent(StartingScreenActivity.this, SingerNameActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ2);
    }
    private void goToRound() {
        Intent intent = new Intent(StartingScreenActivity.this, RoundActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ROUND);
    }
    public void changeGenre(View view) {
        Intent intent = new Intent(StartingScreenActivity.this, GenreActivity.class);
        startActivity(intent);
    }


    public void goToLyricsJumble(View view) {
        Intent intent = new Intent(StartingScreenActivity.this, LyricsJumbleActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    public void goToSongName(View view) {
        Intent intent = new Intent(StartingScreenActivity.this, SongNameActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == LyricsJumbleActivity.RESULT_OK) {
                int JumbleLyricScore = data.getIntExtra(LyricsJumbleActivity.EXTRA_SCORE1, 0);
                updateJumbleLyricHighScore(JumbleLyricScore);
            }

        } else if (requestCode == REQUEST_CODE_QUIZ2) {
            if (resultCode == SingerNameActivity.RESULT_OK) {
                int SingerNameScore = data.getIntExtra(SingerNameActivity.EXTRA_SCORE2, 0);
                updateSingerNameHighScore(SingerNameScore);
            }
        } else if (requestCode == REQUEST_CODE_QUIZ3) {
            if (resultCode == SongNameActivity.RESULT_OK) {
                int songNameScore = data.getIntExtra(SongNameActivity.EXTRA_SCORE3, 0);
                //coin=data.getLongExtra(SongNameActivity.EXTRA_SCORE33,0);
                updateSongNameHighScore(songNameScore);

            }

        } else if (requestCode == REQUEST_CODE_ROUND) {
            if (resultCode == RoundActivity.RESULT_OK) {

                int newScore = data.getIntExtra(RoundActivity.EXTRA_SCORE10, 0);
                switch (RoundActivity.type)
                {
                    case 1 :
                        updateSingerNameHighScore(newScore);
                        break;
                    case 2:
                        updateSongNameHighScore(newScore);
                        break;
                    case 3:
                        updateJumbleLyricHighScore(newScore);
                        break;

                }

            }

        }
        updateCoins();
    }

    private void loadJumbleLyricHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        JumbleLyricHighscore = prefs.getInt(KEY_HIGHSCORE, 0);
        lyricsJumble.setText("" + JumbleLyricHighscore);

    }

    private void updateJumbleLyricHighScore(int newScore) {
        int x=JumbleLyricHighscore;
        JumbleLyricHighscore = newScore;

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(x<JumbleLyricHighscore){
            lyricsJumble.setText(JumbleLyricHighscore+ "");
            editor.putInt(KEY_HIGHSCORE, JumbleLyricHighscore);
            editor.apply();}
        else JumbleLyricHighscore=x;
    }

    private void updateSingerNameHighScore(int newScore) {
        int x=singerNameHighScore;
        singerNameHighScore = newScore;

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(x<singerNameHighScore){
            textViewSongNameHighScore.setText(singerNameHighScore + "");
            editor.putInt(KEY_HIGHSCORE2, singerNameHighScore);
            editor.apply();}
        else singerNameHighScore=x;
    }

    private void loadSingerNameHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        singerNameHighScore = prefs.getInt(KEY_HIGHSCORE2, 0);
        textViewSingerNameHighScore.setText("" + singerNameHighScore);

    }

    private void updateSongNameHighScore(int newScore) {
        int x=songNameHighScore;
        songNameHighScore = newScore;

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        Toast.makeText(StartingScreenActivity.this,String.valueOf(newScore),Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = prefs.edit();
        if(x<songNameHighScore){
            textViewSongNameHighScore.setText(songNameHighScore + "");
            editor.putInt(KEY_HIGHSCORE3, songNameHighScore);
            editor.apply();}
        else songNameHighScore=x;
    }

    private void loadSongNameHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        songNameHighScore = prefs.getInt(KEY_HIGHSCORE3, 0);
        textViewSongNameHighScore.setText("" + songNameHighScore);

    }

    private void loadCoins() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_COIN, MODE_PRIVATE);
        coin = prefs.getLong(KEY_COIN, 0);
        textViewCoin.setText("" + coin);
    }

    private void updateCoins() {
        textViewCoin.setText(coin + "");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_COIN, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(KEY_COIN, coin);
        editor.apply();
    }



}