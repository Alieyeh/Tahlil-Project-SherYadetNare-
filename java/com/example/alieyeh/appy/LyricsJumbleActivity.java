package com.example.nazanin_sarrafzadeh.sheroyadete;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class LyricsJumbleActivity extends AppCompatActivity {

    private ArrayList<String> answerTextViews=new ArrayList<>();
    private ArrayList<TextView> showableTextviews=new ArrayList<>();
    private boolean undoAnswer=false,gotHelp=false;
    private ArrayList<Button> mButtons;
    private String originalText="";
    private SeekBar seekBar;
    private String[] withoutSpaces,correctText;
    private int[] selectedStates;
    private Button score,selection,cb = null;
    private ImageButton play,stop,replay,help,timerhelp;
    private Handler waitForColoring;
    private Runnable restart;
    private ArrayList<Button> selections=new ArrayList<>();
    private TextView timer,correctAnswer,beforeGame,answerField,selectedView;
    private int count=0,countSize=0,numOfChangableViews=0,LyricsJumbleScore= StartingScreenActivity.JumbleLyricHighscore;
    public static final String EXTRA_SCORE1 = "lyricJumbleExtraScore";
    private static final int COUNTDOWN_BEFORE_STARTING=4000,COUNTDOWN_WHILE_PLAYING=16000;
    private CountDownTimer whilePlaying;
    private long timeLeftToStart,timeLeftToFinish;
    private ColorStateList textColorDefaultCD;
    private MusicManager music;
    private GridView gridView,gridTextView;
    private GameDbHelper gameDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_jumble);
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        help= (ImageButton)findViewById(R.id.help);
        timerhelp=(ImageButton)findViewById(R.id.timerHelp);
        music=new MusicManager(seekBar);
        waitForColoring=new Handler();
        gameDbHelper=new GameDbHelper(this);
        correctAnswer=(TextView)findViewById(R.id.correctAnswer);
        timer=(TextView)findViewById(R.id.text_view_countdown);
        textColorDefaultCD=timer.getTextColors();
        score=(Button) findViewById(R.id.scorebutton);
        beforeGame=(TextView)findViewById(R.id.beforeGame);
        play=(ImageButton)findViewById(R.id.play);
        stop=(ImageButton)findViewById(R.id.pause);
        replay=(ImageButton)findViewById(R.id.reset);
        play.setOnClickListener(music.play);
        stop.setOnClickListener(music.stop);
        replay.setOnClickListener(music.reset);
        timerhelp.setEnabled(false);
        help.setEnabled(false);
        play.setEnabled(false);
        stop.setEnabled(false);
        replay.setEnabled(false);
        timerhelp.setVisibility(View.GONE);
        play.setVisibility(View.GONE);
        stop.setVisibility(View.GONE);
        replay.setVisibility(View.GONE);
        timeLeftToStart=COUNTDOWN_BEFORE_STARTING;

        startGame();
      //  CountBeforeStarting();

    }



//    private void CountBeforeStarting(){
//        beforeStarting=new CountDownTimer(timeLeftToStart,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                seekBar.setEnabled(false);
//                timeLeftToStart=millisUntilFinished;
//                changeText();
//            }
//
//            @Override
//            public void onFinish() {
//                timeLeftToStart=3000;
//                beforeGame.setText(null);
//                startGame();
//            }
//        }.start();
//    }

    private void startGame(){
     //   game_db_helper gameDbHelper=new game_db_helper(this);
        music.generateRandomId();
        score.setText(""+LyricsJumbleScore);
        music.playTheMusic(this);
        seekBar.setEnabled(true);
        music.song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                count++;
                if (count == 1) {
                    setupViewForFirstRound();
                }
            }
        });


    }

    public void setupViewForFirstRound(){
        displayWords();
        displayAnswerFields();
        //show and enable buttons after words are displayed
        help.setEnabled(true);
        play.setEnabled(true);
        stop.setEnabled(true);
        replay.setEnabled(true);
        play.setVisibility(View.VISIBLE);
        stop.setVisibility(View.VISIBLE);
        replay.setVisibility(View.VISIBLE);
        timeLeftToFinish=COUNTDOWN_WHILE_PLAYING;
        countWhilePlaying();
    }



    private void countWhilePlaying(){
        gotHelp=false;
        whilePlaying= new CountDownTimer(timeLeftToFinish, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftToFinish=millisUntilFinished;
                changeTimer();

            }

            @Override
            public void onFinish() {
                timeLeftToFinish=0;
                timer.setText("00:00");
            //    if (gotHelp==true) {
                    gridTextView.setAdapter(null);
                    showAnswer();
                    seekBar.setProgress(0);
                    restart = new Runnable() {
                        @Override
                        public void run() {
                            playAgain();
                        }
                    };
                    waitForColoring.postDelayed(restart, 2000);
                }
        //    }
        }.start();

    }

    private void changeTimer(){

        int minutes = (int) (timeLeftToFinish/ 1000) / 60;
        int seconds = (int) (timeLeftToFinish / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeFormatted);
        if (timeLeftToFinish <= 8000) {
            timer.setTextColor(Color.RED);
            timerhelp.setEnabled(true);
            timerhelp.setVisibility(View.VISIBLE);
        }
    }

    private void changeText(){

        int seconds = (int) (timeLeftToStart / 1000) % 60;
        //   String timeFormatted = String.format(Locale.getDefault(),"%02d",seconds);
        beforeGame.setText(seconds+"");
    }

    private void displayAnswerFields(){
        for (int i = 0; i <withoutSpaces.length ; i++) {
            answerField = new TextView(this);
            answerField.setRotationY(180);
            answerField.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
            answerField.setTextColor(Color.YELLOW);
            answerField.setBackgroundResource(R.drawable.textview);
            answerField.setGravity(Gravity.CENTER);
            answerField.setId(i);
            answerField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedView=(TextView)v;
                    undoAnswer=true;
                    numOfChangableViews++;
                    for (int i = 0; i <withoutSpaces.length ; i++) {
                        if (mButtons.get(i).getText().equals(selectedView.getText()) &&
                                mButtons.get(i).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.select).getConstantState())){
                                selectedStates[i]=0;
                                selectedView.setText("");
                                answerTextViews.set(selectedView.getId(),"");
                                mButtons.get(i).setBackgroundResource(R.drawable.words);
                                selections.remove(mButtons.get(i));
                                break;
                        }
                    }
                }
            });
            showableTextviews.add(answerField);
        }
        gridTextView = (GridView) findViewById(R.id.ansgrid);
        gridTextView.setAdapter(new AnswerGrid(showableTextviews));
        gridTextView.setRotationY(180);
    }

    private void displayWords(){

        mButtons=new ArrayList<>();
        originalText=gameDbHelper.giveTheLyric(music.randomRow);
        correctText = originalText.split(" ");
        withoutSpaces = originalText.split(" ");

        //shuffle sentence
        Collections.shuffle(Arrays.asList(withoutSpaces));

        for (int i = 0; i < withoutSpaces.length; i++) {
            cb = new Button(this);
            cb.setText(withoutSpaces[i]);
            cb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
            cb.setBackgroundResource(R.drawable.words);
            cb.setId(i);
            selectedStates=new int[withoutSpaces.length];
            selectedStates[i]=0;
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //see which button was clicked
                    selection = (Button)v;
                    //add the selected button into the array and store all selected buttons
                    selections.add(selection);
                    if (undoAnswer==false){
                        countSize++;
                    }

                    answerCycle();
                }
            });
            mButtons.add(cb);
        }
        //make gridview and show buttons
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ButtonGrid(mButtons));
    }

    private void answerCycle(){
        int index=selection.getId();

        if (selectedStates[index]==0){
            selectedStates[index]=1;
            for (int i = 0; i <countSize ; i++) {

                if (undoAnswer == true && answerTextViews.get(i) == "") {
                    numOfChangableViews--;
                    if (numOfChangableViews==0){
                        undoAnswer=false;
                    }
                    answerTextViews.set(i,(String)selection.getText());
                    showableTextviews.get(i).setText(answerTextViews.get(i));
                    selection.setBackgroundResource(R.drawable.select);
                    break;
                } else if (showableTextviews.get(i).getText() == "") {

                    answerTextViews.add(i,(String) selection.getText());
                    showableTextviews.get(i).setText(answerTextViews.get(i));
                    selection.setBackgroundResource(R.drawable.select);
                    if (answerTextViews.size()==correctText.length) {
                        gridTextView.setAdapter(null);
                        if (checkAnswerState()) {
                            displayWinnerState();
                            break;
                        }
                        else {
                            displayLooserState();
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean checkAnswerState(){
        if (Arrays.equals(answerTextViews.toArray(),correctText))
            return true;
        else
            return false;
    }

    private void showAnswer(){
        correctAnswer.setText(originalText);
        correctAnswer.setTextColor(Color.GREEN);
    }

    private void displayLooserState(){
        for (Button s:selections) {
            s.setBackgroundResource(R.drawable.finish);
            showAnswer();
        }
     //
        whilePlaying.cancel();
       music.checkForFreeze();
        restart=new Runnable() {
            @Override
            public void run() {
                playAgain();
            }
        };
        waitForColoring.postDelayed(restart,2000);
    }

    private void displayWinnerState(){
        for (Button s:selections) {
            s.setBackgroundResource(R.drawable.win);
            showAnswer();
        }
        LyricsJumbleScore+=5;
        score.setText(""+LyricsJumbleScore);
        sendScoreToMenue();
        whilePlaying.cancel();
        music.checkForFreeze();
        restart=new Runnable() {
            @Override
            public void run() {
                playAgain();
            }
        };
        waitForColoring.postDelayed(restart,2000);
    }

    private void playAgain(){
        //reset everything
       music.checkForFreeze();
        mButtons.clear();
        showableTextviews.clear();
        gridView.setAdapter(null);
        answerTextViews.clear();
        countSize=0;
        numOfChangableViews=0;
        correctAnswer.setText(null);
        count=0;
        undoAnswer=false;
        gotHelp=false;
        timeLeftToFinish=COUNTDOWN_WHILE_PLAYING;
        timer.setText("00:15");
        timer.setTextColor(textColorDefaultCD);
        help.setEnabled(false);
        play.setEnabled(false);
        stop.setEnabled(false);
        replay.setEnabled(false);
        play.setVisibility(View.GONE);
        stop.setVisibility(View.GONE);
        replay.setVisibility(View.GONE);
        startGame();

    }


    private void sendScoreToMenue(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE1, LyricsJumbleScore);
        setResult(RESULT_OK, resultIntent);
    }

    public void giveMeMoreTime(View view){
        gotHelp=true;
        if (LyricsJumbleScore>=3) {
            LyricsJumbleScore -= 3;
            score.setText(""+LyricsJumbleScore);
            sendScoreToMenue();
            whilePlaying.cancel();
            timeLeftToFinish = COUNTDOWN_WHILE_PLAYING;
            timer.setTextColor(textColorDefaultCD);
            countWhilePlaying();
        }
        else {
            Toast.makeText(LyricsJumbleActivity.this,"سکه هات کافی نیس :)",Toast.LENGTH_SHORT).show();
        }
    }

    public void helpMe(View view){
//        gotHelp=true;
//        if (LyricsJumbleScore>=3) {
//            LyricsJumbleScore -= 3;
//            score.setText(""+LyricsJumbleScore);
//            sendScoreToMenue();
//            whilePlaying.cancel();
//            timeLeftToFinish = COUNTDOWN_WHILE_PLAYING;
//            timer.setTextColor(textColorDefaultCD);
//            countWhilePlaying();
//        }
//        else {
//            Toast.makeText(LyricsJumbleActivity.this,"سکه هات کافی نیس :)",Toast.LENGTH_SHORT).show();
//        }

    }


    public void onDestroy(){
        super.onDestroy();
        music.checkForDestroy();
        if (whilePlaying !=null){
            whilePlaying.cancel();
        }
//        if (beforeStarting != null) {
//            beforeStarting.cancel();
//        }

    }

}
