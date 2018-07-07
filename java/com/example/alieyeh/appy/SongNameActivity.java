package com.example.nazanin_sarrafzadeh.faze4;
//

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class SongNameActivity extends AppCompatActivity {
    private static final long TIMER_IN_MILLIS = 10000;
    private final long TIME_TO_SHOW_ANSWER = 1000;
    static int songNameScore;
    private TextView answ;
    private Runnable restart;
    private Handler waitForColoring;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb[];
    TextView timerTextView;
    TextView textViewCoin;
    private Button buttonSongNameScore;
    private Button buttonCoin;
    private ImageButton help;
    private int part = 1;
    private ColorStateList textColorTimer;
    private ColorStateList textColorRadioButton;
    CountDownTimer countDownTimer;
    CountDownTimer showAnswerTimer;
    private long timeLeftInMillis;
    private long showAnsTimeLeft;
    static Context context;
    private int randomRow = 1, count = 0, pause;
    public static final String EXTRA_SCORE3 = "songNameExtraScore";
    public static final String EXTRA_SCORE33 = "CoinssExtraScore";
    private  TextView ansPerSet;
    private ArrayList<TextView> fields=new ArrayList<>();
    int ansnum;
    String ans = "";
    MultipleChoice mc = new MultipleChoice();
    MusicManager musicManager = new MusicManager();
    Help helpMe = new Help();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waitForColoring=new Handler();
        setContentView(R.layout.activity_song_name);
        buttonSongNameScore = (Button) findViewById(R.id.scoreImg);
        timerTextView = (TextView) findViewById(R.id.text_view_timer);
        songNameScore = 0;
        answ = (TextView) findViewById(R.id.ans);
        rbGroup = (RadioGroup) findViewById(R.id.radiogroupp);
        rb1 = (RadioButton) findViewById(R.id.option1);
        rb2 = (RadioButton) findViewById(R.id.option2);
        rb3 = (RadioButton) findViewById(R.id.option3);
        rb4 = (RadioButton) findViewById(R.id.option4);
        textViewCoin = (TextView)findViewById(R.id.coin);
        textViewCoin.setText(StartingScreenActivity.coin + "");
        textColorRadioButton = rb1.getTextColors();
        textColorTimer = timerTextView.getTextColors();
        context = this;
        help = (ImageButton) findViewById(R.id.help);

        displayQuestionPerSet();

        startGame();
    }

    private void displayQuestionPerSet(){
        LinearLayout answer_layout = (LinearLayout)findViewById(R.id.ansfield);

        for(int i = 0 ; i<3; i++)
        {
            ansPerSet= new TextView(this);
            ansPerSet.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ansPerSet.setBackgroundResource(R.drawable.ans_per_set);
            ansPerSet.setId(i);
            ansPerSet.setWidth(50);
            ansPerSet.setHeight(50);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10,10, 10);
            ansPerSet.setLayoutParams(params);
            fields.add(ansPerSet);
            answer_layout.addView(ansPerSet);

        }
    }


    private void startGame() {
        musicManager.playMusic(this);
        mc.start(this);
        randomRow = mc.randomRow;
        buttonSongNameScore.setText(songNameScore + "");
        showOptions();
        showNextQuestion();


    }


    public void playAgain() {
        //reset everything


        if (part == 3) {
            //if (songNameScore > StartingScreenActivity.songNameHighScore) {
            sendScoreToMenu();
            //}
            part = 1;
            RoundActivity.type = 2;
            RoundActivity.currentScore = songNameScore;
            Intent intent = new Intent(SongNameActivity.this, RoundActivity.class);
            startActivity(intent);
            finish();
        } else {
            rb1.setBackgroundResource(R.drawable.orange_rounded_corner);
            rb2.setBackgroundResource(R.drawable.orange_rounded_corner);
            rb3.setBackgroundResource(R.drawable.orange_rounded_corner);
            rb4.setBackgroundResource(R.drawable.orange_rounded_corner);
            rb1.setEnabled(true);
            rb2.setEnabled(true);
            rb3.setEnabled(true);
            rb4.setEnabled(true);
            timeLeftInMillis = TIMER_IN_MILLIS;
            count = 0;
            musicManager.Stop();
            startGame();
            part++;
        }

    }


    private void checkAnswer() {
        countDownTimer.cancel();
        RadioButton rbSelected = (RadioButton) findViewById(rbGroup.getCheckedRadioButtonId());
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);

        int selectedRbNum = rbGroup.indexOfChild(rbSelected);
        boolean correct = mc.checkAnswer(selectedRbNum);
        if (correct) {
            songNameScore += 10 * (int) (timeLeftInMillis / 1000);
            fields.get(part-1).setBackgroundResource(R.drawable.green_per_set);
        }
        else {
            fields.get(part-1).setBackgroundResource(R.drawable.red_per_set);
        }

        showRightAnswer();
    }

    private void showRightAnswer() {

        countDownTimer.cancel();
        if (rb1.isChecked()) {
            rb1.setBackgroundResource(R.drawable.wrong_ans);
        } else if (rb2.isChecked()) {
            rb2.setBackgroundResource(R.drawable.wrong_ans);
        } else if (rb3.isChecked()) {
            rb3.setBackgroundResource(R.drawable.wrong_ans);
        } else if (rb4.isChecked()) {
            rb4.setBackgroundResource(R.drawable.wrong_ans);

        }

        switch (ansnum) {
            case 0:
                rb1.setBackgroundResource(R.drawable.correct_ans);

                break;
            case 1:
                rb2.setBackgroundResource(R.drawable.correct_ans);

                break;
            case 2:
                rb3.setBackgroundResource(R.drawable.correct_ans);

                break;
            case 3:
                rb4.setBackgroundResource(R.drawable.correct_ans);

                break;
        }
        restart=new Runnable() {
            @Override
            public void run() {
                playAgain();
            }
        };
        waitForColoring.postDelayed(restart,2000);


    }


    private void showOptions() {
        ans = mc.makeSongNameOptions();
        ansnum = mc.ansnum;
        int possCount = 0;

        switch (ansnum) {
            case 0:
                rb1.setText("    " + ans + "    ");
                break;
            case 1:
                rb2.setText("    " + ans + "    ");
                break;
            case 2:
                rb3.setText("    " + ans + "    ");
                break;
            case 3:
                rb4.setText("    " + ans + "    ");
                break;
        }
        for (int i = 0; i < 4; i++) {
            if (i != ansnum) {
                switch (i) {
                    case 0:
                        rb1.setText("    " + mc.options.get(possCount) + "    ");
                        break;
                    case 1:
                        rb2.setText("    " + mc.options.get(possCount) + "    ");
                        break;
                    case 2:
                        rb3.setText("    " + mc.options.get(possCount) + "    ");
                        break;
                    case 3:
                        rb4.setText("    " + mc.options.get(possCount) + "    ");
                        break;

                }
                possCount++;
            }
        }
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorRadioButton);
        rb2.setTextColor(textColorRadioButton);
        rb3.setTextColor(textColorRadioButton);
        rb4.setTextColor(textColorRadioButton);
        rbGroup.clearCheck();
        answ.setText("");
        //answered = false;
        timeLeftInMillis = TIMER_IN_MILLIS;
        showAnsTimeLeft = TIME_TO_SHOW_ANSWER;
        startCountDown();

    }

    /////////////////
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateTimerText()
                ;
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateTimerText();
                showRightAnswer();

            }
        }.start();
    }

    private void updateTimerText() {
        int min = (int) timeLeftInMillis / 1000 / 60;
        int sec = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormat = String.format(Locale.getDefault(), "%2d : %2d", min, sec);
        timerTextView.setText(timeFormat);
        if (timeLeftInMillis <= 6000) {
            timerTextView.setTextColor(Color.RED);
        } else {
            timerTextView.setTextColor(textColorTimer);
        }
    }


    public void confirmtest(View view) {
        //if (!answered) {
        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
            checkAnswer();
        } else {
            Toast.makeText(SongNameActivity.this, "choose", Toast.LENGTH_SHORT).show();
        }
//        } else {
//            playAgain();
//        }
    }

    private void sendScoreToMenu() {
        Intent resultIntent = new Intent(SongNameActivity.this,StartingScreenActivity.class);
        resultIntent.putExtra(EXTRA_SCORE3, songNameScore);
        setResult(RESULT_OK, resultIntent);
    }
    private void sendCoinsToMenu() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE33, StartingScreenActivity.coin);
        setResult(RESULT_OK, resultIntent);
    }

    public void onDestroy() {
        super.onDestroy();
        if (musicManager.song != null)
            musicManager.song.release();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void helpMe(final View view) {
        PopupMenu popup = new PopupMenu(SongNameActivity.this, help);
        popup.getMenuInflater().inflate(R.menu.help_menu2, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        if (StartingScreenActivity.coin >= 10) {
                            StartingScreenActivity.coin -= 10;
                            buttonCoin.setText(StartingScreenActivity.coin + "");

                            helpMe.omitAns(1, ansnum);
                            for (int i = 0; i < 1; i++) {
                                switch (helpMe.omittedAns1) {
                                    case 0:
                                        rb1.setBackgroundResource(R.drawable.checked);
                                        rb1.setEnabled(false);
                                        break;
                                    case 1:
                                        rb2.setBackgroundResource(R.drawable.checked);
                                        rb2.setEnabled(false);
                                        break;
                                    case 2:
                                        rb3.setBackgroundResource(R.drawable.checked);
                                        rb3.setEnabled(false);
                                        break;
                                    case 3:
                                        rb4.setBackgroundResource(R.drawable.checked);
                                        rb4.setEnabled(false);
                                        break;

                                }
                            }

                        } else
                            Toast.makeText(SongNameActivity.this, "حداقل 10 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.two:
                        if (StartingScreenActivity.coin >= 20) {
                            StartingScreenActivity.coin -= 20;
                            buttonCoin.setText(StartingScreenActivity.coin + "");

                            helpMe.omitAns(2, ansnum);
                            switch (helpMe.omittedAns1) {
                                case 0:
                                    rb1.setBackgroundResource(R.drawable.checked);
                                    rb1.setEnabled(false);
                                    break;
                                case 1:
                                    rb2.setBackgroundResource(R.drawable.checked);
                                    rb2.setEnabled(false);
                                    break;
                                case 2:
                                    rb3.setBackgroundResource(R.drawable.checked);
                                    rb3.setEnabled(false);
                                    break;
                                case 3:
                                    rb4.setBackgroundResource(R.drawable.checked);
                                    rb4.setEnabled(false);
                                    break;
                            }
                            switch (helpMe.omittedAns2) {
                                case 0:
                                    rb1.setBackgroundResource(R.drawable.checked);
                                    rb1.setEnabled(false);
                                    break;
                                case 1:
                                    rb2.setBackgroundResource(R.drawable.checked);
                                    rb2.setEnabled(false);
                                    break;
                                case 2:
                                    rb3.setBackgroundResource(R.drawable.checked);
                                    rb3.setEnabled(false);
                                    break;
                                case 3:
                                    rb4.setBackgroundResource(R.drawable.checked);
                                    rb4.setEnabled(false);
                                    break;

                            }

                        } else
                            Toast.makeText(SongNameActivity.this, "حداقل 20 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.three:

                        if (StartingScreenActivity.coin >= 30) {
                            StartingScreenActivity.coin -= 30;

                            buttonCoin.setText(StartingScreenActivity.coin + "");
                            switch (ansnum) {
                                case 0:
                                    rb1.setChecked(true);
                                    break;
                                case 1:
                                    rb2.setChecked(true);

                                    break;
                                case 2:
                                    rb3.setChecked(true);

                                    break;
                                case 3:
                                    rb4.setChecked(true);

                                    break;

                            }
                            rb1.setEnabled(false);
                            rb2.setEnabled(false);
                            rb3.setEnabled(false);
                            rb4.setEnabled(false);

                            checkAnswer();

                        } else
                            Toast.makeText(SongNameActivity.this, "حداقل 30 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                        return true;
                }
                sendScoreToMenu();
                return false;
            }
        });

        popup.show();
    }


}
