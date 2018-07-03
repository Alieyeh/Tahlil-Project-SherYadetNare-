package com.example.alieyeh.appy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;import android.view.View;

public class RoundActivity extends AppCompatActivity {
    Button toAgain;
    Button toMenu;
    Button toGenre;
    static int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        toAgain= findViewById(R.id.goPlay);
        toMenu= findViewById(R.id.goMenu);
        toGenre= findViewById(R.id.goGenre);
    }
    public void goToMenu(final View view){
        Intent intent=new Intent(RoundActivity.this,StartingScreenActivity.class);
        startActivity(intent);
    }
    public void goToGenre(final View view){
        //Intent intent=new Intent(RoundActivity.this,Genre.class);
        //startActivity(intent);
    }
    public void goAgain(final View view){
        Intent intent;
        switch(type) {
            case 1:
                intent = new Intent(RoundActivity.this,SingerNameActivity.class);
                startActivity(intent);break;
            case 2:
                intent = new Intent(RoundActivity.this, SongNameActivity.class);
            startActivity(intent); break;
            case 3:
                intent = new Intent(RoundActivity.this,LyricsJumbleActivity.class );
                startActivity(intent);break;
        }
    }
}
