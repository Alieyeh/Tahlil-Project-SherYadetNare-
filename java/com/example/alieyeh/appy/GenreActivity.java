package com.example.alieyeh.appy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

    }

    public void setGenre(View view){
        switch (view.getId()){
            case R.id.khaz :
                SongNameActivity.genre=1;
                SingerNameActivity.genre=1;
                LyricsJumbleActivity.genre=1;

                break;
            case R.id.pop :
                SongNameActivity.genre=2;
                SingerNameActivity.genre=2;
                LyricsJumbleActivity.genre=2;
                break;
            case R.id.sonati :
                SongNameActivity.genre=3;
                SingerNameActivity.genre=3;
                LyricsJumbleActivity.genre=3;
                break;
            case R.id.titraj :
                SongNameActivity.genre=4;
                SingerNameActivity.genre=4;
                LyricsJumbleActivity.genre=4;
                break;
        }
        Intent intent = new Intent(GenreActivity.this, StartingScreenActivity.class);
        startActivity(intent);
        finish();

    }
}
