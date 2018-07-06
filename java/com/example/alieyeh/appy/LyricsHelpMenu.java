package com.example.nazanin_sarrafzadeh.faze4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by nazanin-sarrafzadeh on 7/3/2018.
 */
public class LyricsHelpMenu extends AppCompatActivity implements View.OnClickListener {

    private Button wordNumber,selected;
    private ArrayList<Button> buttons;
    private GridView gridView;
    private int numberOfWords;
    private Help help;
    public static final String NUMBER = "whichword";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyrics_help_menu);
        help=new Help();
        buttons =new ArrayList<>();
        int width=460;
        int height=WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().setLayout(width,height);

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=260;
        params.y=-530;
        getWindow().setAttributes(params);
        Intent intent=getIntent();
        numberOfWords=intent.getIntExtra("numberOfWords", 0);
        showNumbers();
    }

    public void showNumbers(){
        for (int i = 0; i <numberOfWords; i++) {
            wordNumber = new Button(this);
            wordNumber.setText(String.valueOf(i+1));
            wordNumber.setId(i);
            wordNumber.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
            wordNumber.setBackgroundResource(R.drawable.helpwords);
            wordNumber.setOnClickListener(this);
            buttons.add(wordNumber);
        }
        gridView = (GridView) findViewById(R.id.wordNumber);
        gridView.setAdapter(new HelpWindow(buttons));
    }

    @Override
    public void onClick(View v) {
        selected=(Button)v;
        int which=selected.getId();
        Intent intent=new Intent();
        intent.putExtra(NUMBER, which);
        setResult(RESULT_OK, intent);
     //   Toast.makeText(LyricsHelpMenu.this,String.valueOf(which),Toast.LENGTH_SHORT).show();
//        intent.putExtra("number",which);
//        startActivityForResult(intent);
        finish();

    }
}

