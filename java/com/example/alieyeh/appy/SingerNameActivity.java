package com.example.alieyeh.appy;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class SingerNameActivity extends AppCompatActivity {
    private static final long TIMER_IN_MILLIS = 10000;
    private final long TIME_TO_SHOW_ANSWER = 1000;
    private TextView textViewSingerNameScore;
    private int singerNameScore = StartingScreenActivity.singerNameCoins;
    private TextView answ;
    private ProgressBar progressBar;
    private int progressStatus = 15;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    TextView timerTextView;
    private Button buttonSingerNameScore;

    private ImageButton help;
    private int questionCounter;
    private ColorStateList textColorTimer;
    private ColorStateList textColorRadioButton;
    CountDownTimer countDownTimer;
    CountDownTimer showAnswerTimer;
    private Runnable restart;
    private Handler waitForColoring;
    private long timeLeftInMillis;
    private long showAnsTimeLeft;
    private boolean answered;
    private List<String> possibleAns;
    private Handler handler;
    static Context context;
    private MediaPlayer song;
    private int randomRow = 1, count = 0, pause;
    public static final String EXTRA_SCORE2 = "singerNameExtraScore";
    int ansnum = 0;
    String ans = "";
    MultipleChoice mc = new MultipleChoice();
    MusicManager musicManager=new MusicManager();
    Help helpMe=new Help();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_name);
        waitForColoring=new Handler();
        buttonSingerNameScore = (Button) findViewById(R.id.scoreImg);
        timerTextView = (TextView) findViewById(R.id.text_view_timer);

        answ = (TextView) findViewById(R.id.ans);
        rbGroup = (RadioGroup) findViewById(R.id.radiogroupp);
        rb1 = (RadioButton) findViewById(R.id.option1);
        rb2 = (RadioButton) findViewById(R.id.option2);
        rb3 = (RadioButton) findViewById(R.id.option3);
        rb4 = (RadioButton) findViewById(R.id.option4);


        textColorRadioButton = rb1.getTextColors();
        textColorTimer = timerTextView.getTextColors();
        context = this;
        help = (ImageButton) findViewById(R.id.help);


        startGame();
    }


    private void startGame() {
        musicManager.playMusic(this);
        mc.startA(this);
        randomRow = mc.randomRow;
        buttonSingerNameScore.setText(singerNameScore + "");
        showOptions();
        showNextQuestion();


    }


    public void playAgain() {
        //reset everything

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
            singerNameScore++;
            buttonSingerNameScore.setText(singerNameScore + "");
            sendScoreToMenu();
            Toast.makeText(this, "باریکلا !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "واه واه :))", Toast.LENGTH_SHORT).show();

        }

        showRightAnswer();
        sendScoreToMenu();
    }
    private void showRightAnswer() {

//        showAnswerTimer = new CountDownTimer(showAnsTimeLeft, 1000) {
//            @Override
//            public void onTick(long l) {
//                showAnsTimeLeft = l;
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

        //   playAgain();
        //         }

//            @Override
//            public void onFinish() {

        //         showAnsTimeLeft = 0;
        //  playAgain();
//            }
//        }.start();



    }
    private void showOptions() {
        ans = mc.makeSingerNameOptions();
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
//        questionCounter++;
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

    /////////////////
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



    private void sendScoreToMenu() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE2, singerNameScore);
        setResult(RESULT_OK, resultIntent);
    }



    public void onDestroy() {
        super.onDestroy();
        song.release();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void confirmtest(View view) {
        if (!answered) {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                checkAnswer();
            } else {
                Toast.makeText(SingerNameActivity.this, "choose", Toast.LENGTH_SHORT).show();
            }
        } else {
            playAgain();
        }
    }
    public void helpMe(final View view) {
    PopupMenu popup = new PopupMenu(SingerNameActivity.this, help);
        popup.getMenuInflater().inflate(R.menu.help_menu2, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.one:
                    if (singerNameScore >= 10) {
                        singerNameScore -= 10;
                        buttonSingerNameScore.setText(singerNameScore + "");

                        helpMe.omitAns(1,ansnum);
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
                        Toast.makeText(SingerNameActivity.this, "حداقل 10 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.two:
                    if (singerNameScore >= 20) {
                        singerNameScore -= 20;
                        buttonSingerNameScore.setText(singerNameScore + "");

                        helpMe.omitAns(2,ansnum);
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
                        Toast.makeText(SingerNameActivity.this, "حداقل 20 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.three:

                    if (singerNameScore >= 30) {
                        singerNameScore -= 30;

                        buttonSingerNameScore.setText(singerNameScore + "");
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
                        Toast.makeText(SingerNameActivity.this, "حداقل 30 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                    return true;
            }
            sendScoreToMenu();
            return false;
        }
    });

        popup.show();
    }

    public void checkedColour(View view) {
        if (rb1.isChecked()) {
            rb1.setBackgroundResource(R.drawable.checked);
        } else {
            rb1.setBackgroundResource(R.drawable.orange_rounded_corner);
        }
        if (rb2.isChecked()) {
            rb2.setBackgroundResource(R.drawable.checked);
        } else {
            rb2.setBackgroundResource(R.drawable.orange_rounded_corner);
        }
        if (rb3.isChecked()) {
            rb3.setBackgroundResource(R.drawable.checked);
        } else {
            rb3.setBackgroundResource(R.drawable.orange_rounded_corner);
        }
        if (rb4.isChecked()) {
            rb4.setBackgroundResource(R.drawable.checked);
        } else {
            rb4.setBackgroundResource(R.drawable.orange_rounded_corner);
        }
    }
}

