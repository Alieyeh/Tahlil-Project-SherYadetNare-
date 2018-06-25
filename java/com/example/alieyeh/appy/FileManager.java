package com.example.nazanin_sarrafzadeh.sheroyadete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazanin-sarrafzadeh on 6/8/2018.
 */
public class FileManager {

    StartingScreenActivity mainActivity = new StartingScreenActivity();


    public ArrayList<byte[]> listSoundFilesBlob() {

        ArrayList<byte[]> soundfiles = new ArrayList<>();
        for (int i = 0; i < listRawMediaFiles().size(); i++) {
            InputStream istream = mainActivity.context.getResources().openRawResource(listRawMediaFiles().get(i));
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

    public ArrayList<String> listTextFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
     //   for (String assetFileName : assetFileNames) {
            try {
                reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("texts.txt")));

                String line = "";
                while ((line = reader.readLine()) != null) {

                    files.add(line);

                }
            }
            catch (IOException e) {
                e.printStackTrace();

            }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

  //      }
        return files;
    }

    public ArrayList<String> listSingerNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        //   for (String assetFileName : assetFileNames) {
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("singer_name.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);

            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //      }
        return files;
    }

    public ArrayList<String> listSongNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        //   for (String assetFileName : assetFileNames) {
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("song_name.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);

            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //      }
        return files;
    }
}
