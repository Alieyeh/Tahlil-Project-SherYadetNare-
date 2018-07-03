package com.example.alieyeh.appy;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Alieyeh on 6/7/18.
 */

public class Help{
    static int omittedAns1,omittedAns2;

     void omitAns(int n,int ansnum) {
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
                omittedAns2 = rand.nextInt(4) ;
                if (omittedAns2 != ansnum && omittedAns2 != omittedAns1) {
                    f2 = true;
                }
            }
            while (!f2);

        }

    }

}
