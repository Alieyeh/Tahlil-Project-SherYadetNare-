package com.example.nazanin_sarrafzadeh.sheryadetnare;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazanin-sarrafzadeh on 5/28/2018.
 */
public class MusicManager {

    starting_screen_activity mainActivity=new starting_screen_activity();
    public ArrayList<byte[]> listSoundFilesBlob(){

        ArrayList<byte[]> soundfiles=new ArrayList<>();
        for (int i = 0; i <listRawMediaFiles().size() ; i++) {
            InputStream istream =mainActivity.context.getResources().openRawResource(listRawMediaFiles().get(i));
            try {
                byte[] music = new byte[istream.available()];
                istream.read(music);
                soundfiles.add(music);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return soundfiles;
    }

    //returns id of songs in asset folder
    public static List<Integer> listRawMediaFiles() {
        List<Integer> ids = new ArrayList<>();
        //what's your problem
        for (Field field : R.raw.class.getFields()) {
            try {
                ids.add(field.getInt(field));
            } catch (Exception e) {

            }
        }
        return ids;
    }

    public ArrayList<String> listTextFiles(){
        ArrayList<String> files=new ArrayList<>();
        AssetManager am =mainActivity.context.getAssets();
        String assetFileNames[] = new String[0];
        try {
            assetFileNames = am.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        for(String assetFileName : assetFileNames) {
            try {
                reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open(assetFileName)));

                String line="";
                while ((line = reader.readLine()) != null) {

                    files.add(line);

                }
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return files;
    }

}
