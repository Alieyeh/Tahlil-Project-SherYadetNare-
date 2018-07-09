package com.example.alieyeh.appy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RoundActivity extends AppCompatActivity {
    Button toAgain;
    Button toMenu;
    Button toGenre;
    static int type;
    static int currentScore, highScr;
    static boolean newHighScore = false;
    ImageView goldenCup;
    TextView textViewScore, textViewHighScore, textViewNewHighScore;

    private static final int REQUEST_CODE_QUIZ = 1;
    private static final int REQUEST_CODE_QUIZ2 = 2;
    private static final int REQUEST_CODE_QUIZ3 = 3;
    private static final int REQUEST_CODE_Coin = 10;

    public static final String EXTRA_SCORE10 = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        toAgain = (Button) findViewById(R.id.goPlay);
        toMenu = (Button) findViewById(R.id.goMenu);
        toGenre = (Button) findViewById(R.id.goGenre);
        textViewScore = (TextView) findViewById(R.id.score);
        goldenCup = findViewById(R.id.goldCup);
        textViewScore.setText(currentScore + " ");
        textViewNewHighScore = findViewById(R.id.newHighScore);
        textViewHighScore = findViewById(R.id.highscore);
        textViewHighScore.setText(highScr + "");

        textViewNewHighScore.setVisibility(View.INVISIBLE);
        goldenCup.setVisibility(View.INVISIBLE);
        if (newHighScore) {
            goldenCup.setVisibility(View.VISIBLE);
            textViewNewHighScore.setVisibility(View.VISIBLE);
        }

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


    private void sendCurrentScoreToMenu() {
        Toast.makeText(RoundActivity.this, String.valueOf(currentScore), Toast.LENGTH_SHORT).show();
        Intent resultIntent = new Intent(RoundActivity.this, StartingScreenActivity.class);
        resultIntent.putExtra(EXTRA_SCORE10, currentScore);
        setResult(RESULT_OK, resultIntent);
        newHighScore = false;
        finish();

    }

    public void goToMenu(final View view) {
        sendCurrentScoreToMenu();
    }

    public void goToGenre(final View view) {
        sendCurrentScoreToMenu();
        Intent intent = new Intent(RoundActivity.this, GenreActivity.class);
        startActivity(intent);
        finish();

    }

    public void goAgain(final View view) {
        newHighScore = false;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(RoundActivity.this, String.valueOf(currentScore), Toast.LENGTH_SHORT).show();
        sendCurrentScoreToMenu();
    }

}
