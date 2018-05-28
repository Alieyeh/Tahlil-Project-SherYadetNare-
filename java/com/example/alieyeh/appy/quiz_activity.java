package com.example.alieyeh.appy;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;


public class quiz_activity extends AppCompatActivity {
    private static final long TIMER_IN_MILLIS = 10000;
    private final long TIME_TO_SHOW_ANSWER = 1000;
    private TextView textViewSingerNameScore;
    private int singerNameScore = starting_screen_activity.singerNameCoins;
    private TextView answ;
    private ProgressBar progressBar;
    private int progressStatus = 15;
    static int omittedAns1, omittedAns2;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    TextView timerTextView;
    private Button buttonSingerNameScore;
    //  private Button again, play, stop, replay, cb = null;
    private ImageButton help;
    private int questionCounter;
    private ColorStateList textColorTimer;
    private ColorStateList textColorRadioButton;
    CountDownTimer countDownTimer;
    CountDownTimer showAnswerTimer;
    private long timeLeftInMillis;
    private long showAnsTimeLeft;
    private boolean answered;
    private List<String> possibleAns;
    private Handler handler;
    static Context context;
    private MediaPlayer song;
    private int randomRow = 1, count = 0, pause;
    public static final String EXTRA_SCORE2 = "singerNameExtraScore";
    static int ansnum = 0;
    String ans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //  textViewSingerNameScore = findViewById(R.id.coins2);
        buttonSingerNameScore = findViewById(R.id.scoreImg);
        timerTextView = findViewById(R.id.text_view_timer);
        // progressBar = findViewById(R.id.progressBar);
        answ = findViewById(R.id.ans);
        rbGroup = findViewById(R.id.radiogroupp);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        //   confirm = findViewById(R.id.confirm);
        // progressBar = findViewById(R.id.progressBar);
        textColorRadioButton = rb1.getTextColors();
        textColorTimer = timerTextView.getTextColors();
        handler = new Handler();
        context = this;
        help = findViewById(R.id.help);
        possibleAns = new ArrayList<>();
        possibleAns.add("چارتار");
        possibleAns.add("ابی");
        possibleAns.add("شجریان");
        possibleAns.add("بهنام بانی");
        possibleAns.add("ویگن");
        possibleAns.add("احسان خواجه امیری");
        possibleAns.add("بنان");
        possibleAns.add("محمد اصفهانی");
        possibleAns.add("سیامک عباسی");
        startGame();
    }


    private void startGame() {
        game_db_helper gameDbHelper = new game_db_helper(this);
        generateRandomId();
        buttonSingerNameScore.setText(singerNameScore + "");
        gameDbHelper.giveTheSong(randomRow);
        ans = gameDbHelper.giveTheSinger(randomRow);
        showNextQuestion();
        ansnum = generateRandomAnsNum();
        playTheMusic();
        makeOptions();
        answered = false;
        song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

    }

    private void generateRandomId() {
        Random randomId = new Random();
        randomRow = randomId.nextInt(59) + 1;
    }

    private int generateRandomAnsNum() {
        Random randomId = new Random();
        return randomId.nextInt(4);
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

        Stop();
        startGame();

    }

    private void playTheMusic() {
        File file = null;
        FileOutputStream fos;
        game_db_helper givebytesounds = new game_db_helper(this);
        try {
            file = File.createTempFile("sound", "sound");
            fos = new FileOutputStream(file);
            fos.write(givebytesounds.giveTheSong(randomRow));
            fos.close();
            Log.d("File", file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        song = MediaPlayer.create(this, Uri.fromFile(file));
        song.start();


    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNum = rbGroup.indexOfChild(rbSelected);

        if (answerNum == ansnum) {
            singerNameScore++;
            // textViewSingerNameScore.setText(singerNameScore + "");
            buttonSingerNameScore.setText(singerNameScore + "");
            sendScoreToMenu();
            Toast.makeText(this, "باریکلا !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "واه واه :))", Toast.LENGTH_SHORT).show();
        }
        showRightAnswer();
        sendScoreToMenu();
    }


    private void makeOptions() {
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
        Collections.shuffle(possibleAns);
        for (int i = 0; i < 4; i++) {
            if (i != ansnum) {
                switch (i) {
                    case 0:
                        if (possibleAns.get(0).equals(ans))
                            rb1.setText("    " + possibleAns.get(4) + "    ");
                        else rb1.setText("    " + possibleAns.get(0) + "    ");
                        break;
                    case 1:
                        if (possibleAns.get(1).equals(ans))
                            rb2.setText("    " + possibleAns.get(5) + "    ");
                        else rb2.setText("    " + possibleAns.get(1) + "    ");
                        break;
                    case 2:
                        if (possibleAns.get(2).equals(ans))
                            rb3.setText("    " + possibleAns.get(6) + "    ");
                        else rb3.setText("    " + possibleAns.get(2) + "    ");
                        break;
                    case 3:
                        if (possibleAns.get(3).equals(ans))
                            rb4.setText("    " + possibleAns.get(7) + "    ");
                        else rb4.setText("    " + possibleAns.get(3) + "    ");
                        break;
                }
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
        answered = false;
        //  confirm.setText("Confirm");
        timeLeftInMillis = TIMER_IN_MILLIS;
        showAnsTimeLeft = TIME_TO_SHOW_ANSWER;
        startCountDown();
        questionCounter++;
        //  startProgressBarCd();
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

    private void showRightAnswer() {

        showAnswerTimer = new CountDownTimer(showAnsTimeLeft, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTick(long l) {
                showAnsTimeLeft = l;
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

                        // rb1.setTextColor(Color.GREEN);
                        //answ.setText("1");
                        break;
                    case 1:
                        //rb2.setTextColor(Color.GREEN);
                        rb2.setBackgroundResource(R.drawable.correct_ans);

                        //answ.setText("2");
                        break;
                    case 2:
                        // rb3.setTextColor(Color.GREEN);
                        rb3.setBackgroundResource(R.drawable.correct_ans);

                        //answ.setText("3");
                        break;
                    case 3:
                        // rb4.setTextColor(Color.GREEN);
                        rb4.setBackgroundResource(R.drawable.correct_ans);

                        //answ.setText("4");
                        break;
                }
            }

            @Override
            public void onFinish() {

                showAnsTimeLeft = 0;
                playAgain();
            }
        }.start();


    }

    private void sendScoreToMenu() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE2, singerNameScore);
        setResult(RESULT_OK, resultIntent);
    }

    public void Play(View view) {
        if (!song.isPlaying()) {
            song.seekTo(pause);
            song.start();
        }
    }

    public void Reset(View view) {
        song.seekTo(0);
        song.start();
    }

    public void Pause(View view) {
        if (song != null) {
            song.pause();
            pause = song.getCurrentPosition();
        }
    }

    public void Stop() {
        if (song != null) {
            song.stop();
            song.reset();
            song.release();
            song = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        song.release();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void confirmtest(View view) {
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);

        if (!answered) {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                checkAnswer();
            } else {
                Toast.makeText(quiz_activity.this, "choose", Toast.LENGTH_SHORT).show();
            }
        } else {
            playAgain();
        }
    }

    public void helpMe(View view) {
        PopupMenu popup = new PopupMenu(quiz_activity.this, help);
        popup.getMenuInflater().inflate(R.menu.help_menu2, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        if (singerNameScore >= 10) {
                            singerNameScore -= 10;
                            buttonSingerNameScore.setText(singerNameScore + "");

                            omitAns(1);
                            for (int i = 0; i < 1; i++) {
                                switch (omittedAns1) {
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
                            Toast.makeText(quiz_activity.this, "حداقل 10 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.two:
                        if (singerNameScore >= 20) {
                            singerNameScore -= 20;
                            buttonSingerNameScore.setText(singerNameScore + "");

                            omitAns(2);
                            switch (omittedAns1) {
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
                            switch (omittedAns2) {
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
                            Toast.makeText(quiz_activity.this, "حداقل 20 تا سکه میخوای !", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(quiz_activity.this, "حداقل 30 تا سکه میخوای !", Toast.LENGTH_SHORT).show();

                        return true;
                }
                sendScoreToMenu();

                return false;
            }
        });

        popup.show();
    }

    private void omitAns(int n) {
        boolean f1 = false, f2 = false;
        Random rand = new Random();

        do {
            omittedAns1 = rand.nextInt(4);
            if (omittedAns1 != ansnum) {
                f1 = true;
            }
        }
        while (!f1);

        if (n == 2) {
            do {
                omittedAns2 = rand.nextInt(4) + 1;
                if (omittedAns2 != ansnum && omittedAns2 != omittedAns1) {
                    f2 = true;
                }
            }
            while (!f2);

        }

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

