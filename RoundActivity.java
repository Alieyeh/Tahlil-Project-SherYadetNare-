package com.example.alieyeh.appy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RoundActivity extends AppCompatActivity {
    Button toAgain;
    Button toMenu;
    Button toGenre;
    static int type;
    static int currentScore;
    TextView textViewScore,textViewCoinsss;

    private static final int REQUEST_CODE_QUIZ = 1;
    private static final int REQUEST_CODE_QUIZ2 = 2;
    private static final int REQUEST_CODE_QUIZ3 = 3;
    private static final int REQUEST_CODE_Coin = 10;

    public static final String EXTRA_SCORE10 = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        toAgain =(Button) findViewById(R.id.goPlay);
        toMenu =(Button) findViewById(R.id.goMenu);
        toGenre =(Button) findViewById(R.id.goGenre);
        textViewScore =(TextView) findViewById(R.id.score);
        textViewScore.setText(currentScore+" ");

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == LyricsJumbleActivity.RESULT_OK) {
                StartingScreenActivity.JumbleLyricHighscore = data.getIntExtra(LyricsJumbleActivity.EXTRA_SCORE1, 0);

            }
        } else if (requestCode == REQUEST_CODE_QUIZ2) {
            if (resultCode == SingerNameActivity.RESULT_OK) {
                StartingScreenActivity.singerNameHighScore = data.getIntExtra(SingerNameActivity.EXTRA_SCORE2, 0);
            }
        } else if (requestCode == REQUEST_CODE_QUIZ3) {
            if (resultCode == SongNameActivity.RESULT_OK) {
                StartingScreenActivity.songNameHighScore = data.getIntExtra(SongNameActivity.EXTRA_SCORE3, 0);
            }

        }
    }


    private void sendSongNameScoreToMenu() {
        Intent resultIntent = new Intent(RoundActivity.this, StartingScreenActivity.class);
        resultIntent.putExtra(EXTRA_SCORE10,currentScore );
        setResult(RESULT_OK, resultIntent);
        Toast.makeText(RoundActivity.this,String.valueOf(currentScore),Toast.LENGTH_SHORT).show();

        finish();

    }
//    private void sendLyricJumbleScoreToMenu() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(EXTRA_SCORE3, songNameScore);
//        setResult(RESULT_OK, resultIntent);
//    }

    public void goToMenu(final View view) {

        sendSongNameScoreToMenu();


    }

    public void goToGenre(final View view) {

    }

    public void goAgain(final View view) {
        finish();
        Intent intent;
        switch (type) {
            case 1:
                intent = new Intent(RoundActivity.this, SingerNameActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(RoundActivity.this, SongNameActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(RoundActivity.this, LyricsJumbleActivity.class);
                startActivity(intent);
                break;
        }
    }

}