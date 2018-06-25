package com.example.nazanin_sarrafzadeh.sheryadetnare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nazanin-sarrafzadeh on 4/3/2018.
 */


public class starting_screen_activity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ=1;
    private static final int REQUEST_CODE_QUIZ2=2;
    private static final int REQUEST_CODE_QUIZ3=3;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    public static final String SHARED_PREFS2 = "singerNameSharedPrefs";
    public static final String KEY_HIGHSCORE2 = "singerNameKeyHighscore";
    public static final String SHARED_PREFS3 = "songNameSharedPrefs";
    public static final String KEY_HIGHSCORE3 = "songNameKeyHighscore";
    public static int JumbleLyricHighscore;
    public static int singerNameCoins;
    public static int songNameCoins;


    static Context context;

    private  TextView textViewSingerNameCoins;
    private TextView lyricsJumble;
    private TextView textViewSongNameCoins;
    //TextView quizSinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        context=this;
        game_db_helper gameDbHelper=new game_db_helper(this);
        Button start=(Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startQuiz();
            }
        });
        lyricsJumble=(TextView)findViewById(R.id.lyricsJumbleScore);
        textViewSingerNameCoins=(TextView)findViewById(R.id.singerNameScore);
        textViewSongNameCoins=(TextView)findViewById(R.id.songNameScore);
        //quizSinger=findViewById(R.id.singerNameScore);
        loadJumbleLyricCoins();
        loadSingerNameCoins();
        loadSongNameCoins();
    }
    private void startQuiz(){
        Intent intent=new Intent(starting_screen_activity.this,quiz_activity.class);
        startActivityForResult(intent,REQUEST_CODE_QUIZ2);
    }
    public void goToLyricsJumble(View view){
        Intent intent=new Intent(starting_screen_activity.this,LyricsJumbleActivity.class);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }
    public void goToSongName(View view){
        Intent intent=new Intent(starting_screen_activity.this,SongNameActivity.class);
        startActivityForResult(intent,REQUEST_CODE_QUIZ3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_QUIZ){
            if (resultCode==LyricsJumbleActivity.RESULT_OK){
                int JumbleLyricScore=data.getIntExtra(LyricsJumbleActivity.EXTRA_SCORE1,0);
                updateJumbleLyricCoins(JumbleLyricScore);}

        }else if(requestCode==REQUEST_CODE_QUIZ2){
            if(resultCode==quiz_activity.RESULT_OK){
                int SingerNameScore=data.getIntExtra(quiz_activity.EXTRA_SCORE2,0);
                updateSingerNameCoins(SingerNameScore);
            }
        }else if(requestCode==REQUEST_CODE_QUIZ3){
            if(resultCode==SongNameActivity.RESULT_OK){
                int SongNameScore=data.getIntExtra(SongNameActivity.EXTRA_SCORE3,0);
                updateSongNameCoins(SongNameScore);
            }
        }
    }
    private void loadJumbleLyricCoins() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        JumbleLyricHighscore = prefs.getInt(KEY_HIGHSCORE, 0);
        lyricsJumble.setText(""+ JumbleLyricHighscore);
    }
    private void updateJumbleLyricCoins(int newScore){
        JumbleLyricHighscore =newScore;
        lyricsJumble.setText(JumbleLyricHighscore +"");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, JumbleLyricHighscore);
        editor.apply();
    }
    private void updateSingerNameCoins(int newScore){
        singerNameCoins =newScore;
        textViewSingerNameCoins.setText(singerNameCoins +"");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE2, singerNameCoins);
        editor.apply();
    }
    private void loadSingerNameCoins() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        singerNameCoins = prefs.getInt(KEY_HIGHSCORE2, 0);
        textViewSingerNameCoins.setText(""+ singerNameCoins);
    }
    private void updateSongNameCoins(int newScore){
        songNameCoins =newScore;
        textViewSongNameCoins.setText(songNameCoins +"");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE3, songNameCoins);
        editor.apply();
    }
    private void loadSongNameCoins() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        songNameCoins = prefs.getInt(KEY_HIGHSCORE3, 0);
        textViewSongNameCoins.setText(""+ songNameCoins);
    }

}