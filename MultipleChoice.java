package com.example.alieyeh.appy;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Accounting on 6/6/2018.
 */

public class MultipleChoice {
    GameDbHelper gameDbHelper;
    public int randomRow;
    int ansnum;
    String rightAnswer;
    List options = new ArrayList();
    Random random;
    private boolean answered;
    List currentRoundMusics = new ArrayList();
    boolean setMusic = true;
    int sets=5;


    void start(Context context, int genre) {
        gameDbHelper = new GameDbHelper(context);
        setMusic=false;
        //if (currentRoundMusics.size() < sets) {

                randomRow = MusicManager.randomRow;
               // if (!currentRoundMusics.contains(randomRow)) {
                    //currentRoundMusics.add(randomRow);
                    gameDbHelper.giveTheSong(randomRow, genre);
                    //setMusic = true;

               // }

//            else {
//
//            }
//        } else if (currentRoundMusics.size() == 10) {
//            currentRoundMusics.clear();
//        }
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

    String makeSongNameOptions(int genre) {
        options.clear();
        rightAnswer = gameDbHelper.giveTheSongName(randomRow, SongNameActivity.genre);
        int randOption = 1;
        while (options.size() < 3) {
            random = new Random();
            switch (genre) {
                case 1:
                    randOption = random.nextInt(FileManager.khazCount) + 1;
                    break;
                case 2:
                    randOption = random.nextInt(FileManager.popCount) + 1;
                    break;
                case 3:
                    randOption = random.nextInt(FileManager.sonatiCount) + 1;
                    break;
                case 4:
                    randOption = random.nextInt(FileManager.titrajCount) + 1;
                    break;
            }


            if (!options.contains(gameDbHelper.giveTheSongName(randOption, SongNameActivity.genre)) && randOption != randomRow) {
                options.add(gameDbHelper.giveTheSongName(randOption, SongNameActivity.genre));
            }

        }
        return rightAnswer;
    }

    String makeSingerNameOptions(int genre) {
        options.clear();
        rightAnswer = gameDbHelper.giveTheSinger(randomRow, SingerNameActivity.genre);
        int randOption = 1;
        while (options.size() < 3) {
            random = new Random();
            switch (genre) {
                case 1:
                    randOption = random.nextInt(FileManager.khazCount) + 1;
                    break;
                case 2:
                    randOption = random.nextInt(FileManager.popCount) + 1;
                    break;
                case 3:
                    randOption = random.nextInt(FileManager.sonatiCount) + 1;
                    break;
                case 4:
                    randOption = random.nextInt(FileManager.titrajCount) + 1;
                    break;
            }

            if (!options.contains(gameDbHelper.giveTheSinger(randOption, SingerNameActivity.genre)) && randOption != randomRow) {
                options.add(gameDbHelper.giveTheSinger(randOption, SingerNameActivity.genre));
            }

        }
        return rightAnswer;
    }


}
