package com.example.alieyeh.appy;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MultipleChoice  {
    GameDbHelper gameDbHelper;
    public int randomRow;
    int ansnum;
    String rightAnswer;
    List options = new ArrayList();
    Random random;
    private boolean answered;


    void startA(Context context) {
        gameDbHelper = new GameDbHelper(context);
        randomRow=MusicManager.randomRow;
        gameDbHelper.giveTheSong(randomRow);

        ansnum = generateRandomAnsNum();
        answered = false;

    }

    boolean checkAnswer(int selectedRb) {
        answered = true;
        if (selectedRb == ansnum) {
            return true;

        } else {
            return false;
        }
    }




    private int generateRandomAnsNum() {
        random = new Random();
        return random.nextInt(4);
    }

    String makeOptions() {
        options.clear();
        rightAnswer = gameDbHelper.giveTheSongName(randomRow);
        int randOption;
        while (options.size() < 3) {
            random = new Random();
            randOption = random.nextInt(59) + 1;
            if (!options.contains(gameDbHelper.giveTheSongName(randOption)) && randOption!=randomRow) {
                options.add(gameDbHelper.giveTheSongName(randOption));
            }

        }
        return rightAnswer;
    }
    String makeOptions2() {
        options.clear();
        rightAnswer = gameDbHelper.giveTheSinger(randomRow);
        int randOption;
        while (options.size() < 3) {
            random = new Random();
            randOption = random.nextInt(59) + 1;
            if (!options.contains(gameDbHelper.giveTheSinger(randOption)) && randOption!=randomRow) {
                options.add(gameDbHelper.giveTheSinger(randOption));
            }

        }
        return rightAnswer;
    }


//    @Override
//    public void onClick(View view) {
//
//        int selectedRaa=0;
//        switch (view.getId()) {
//            case R.id.option1:
//                selectedRaa = 0;
//                break;
//            case R.id.option2:
//                selectedRaa = 1;
//                break;
//            case R.id.option3:
//                selectedRaa = 2;
//                break;
//            case R.id.option4:
//                selectedRaa = 3;
//                break;
//        }
//        checkAnswer(selectedRaa);
//    }
}
