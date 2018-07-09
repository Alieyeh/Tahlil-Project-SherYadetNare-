package com.example.alieyeh.appy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.*;

/**
 * Created by nazanin-sarrafzadeh on 6/8/2018.
 */
public class FileManager {

    StartingScreenActivity mainActivity = new StartingScreenActivity();
    static int popCount=35,sonatiCount=18,khazCount=11,titrajCount=7;


    public ArrayList<byte[]> listKhazSoundFilesBlob() {

        ArrayList<byte[]> soundfiles = new ArrayList<>();
        for (int i = 0; i < listKhazRawMediaFiles().size(); i++) {
            InputStream istream = mainActivity.context.getResources().openRawResource(listKhazRawMediaFiles().get(i));
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
    public ArrayList<byte[]> listPopSoundFilesBlob() {

        ArrayList<byte[]> soundfiles = new ArrayList<>();
        for (int i = 0; i < listPopRawMediaFiles().size(); i++) {
            InputStream istream = mainActivity.context.getResources().openRawResource(listPopRawMediaFiles().get(i));
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
    public ArrayList<byte[]> listSonatiSoundFilesBlob() {

        ArrayList<byte[]> soundfiles = new ArrayList<>();
        for (int i = 0; i < listSonatiRawMediaFiles().size(); i++) {
            InputStream istream = mainActivity.context.getResources().openRawResource(listSonatiRawMediaFiles().get(i));
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
    public ArrayList<byte[]> listTitrajSoundFilesBlob() {

        ArrayList<byte[]> soundfiles = new ArrayList<>();
        for (int i = 0; i < listTitrajRawMediaFiles().size(); i++) {
            InputStream istream = mainActivity.context.getResources().openRawResource(listTitrajRawMediaFiles().get(i));
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
    public static List<Integer> listKhazRawMediaFiles() {
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
    public static List<Integer> listPopRawMediaFiles() {
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
    public static List<Integer> listSonatiRawMediaFiles() {
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
    public static List<Integer> listTitrajRawMediaFiles() {
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

/////////////////////////////////////
    public ArrayList<String> listPopTextFiles() {

        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("texts_pop.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);
                //popCount++;

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

        //      }
        return files;
    }

    public ArrayList<String> listPopSingerNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("singer_name_pop.txt")));

            String line = "";
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

        return files;
    }

    public ArrayList<String> listPopSongNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("song_name_pop.txt")));

            String line = "";
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

        return files;
    }


    /////////////////////////////////////////
    public ArrayList<String> listSonatiTextFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("texts_sonati.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);
                //sonatiCount++;

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

        //      }
        return files;
    }

    public ArrayList<String> listSonatiSingerNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("singer_name_sonati.txt")));

            String line = "";
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

        //      }
        return files;
    }

    public ArrayList<String> listSonatiSongNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("song_name_sonati.txt")));

            String line = "";
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

        //      }
        return files;
    }

    ////////////////////////////////
    public ArrayList<String> listTitrajTextFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("texts_titraj.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);
                //titrajCount++;

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

        return files;
    }

    public ArrayList<String> listTitrajSingerNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("singer_name_titraj.txt")));

            String line = "";
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

        //      }
        return files;
    }

    public ArrayList<String> listTitrajSongNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("song_name_titraj.txt")));

            String line = "";
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

        return files;
    }

    ////////////////////////////////////////////////
    public ArrayList<String> listKhazTextFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("texts_khaz.txt")));

            String line = "";
            while ((line = reader.readLine()) != null) {

                files.add(line);
                //khazCount++;

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

        return files;
    }

    public ArrayList<String> listKhazSingerNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("singer_name_khaz.txt")));

            String line = "";
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

        return files;
    }

    public ArrayList<String> listKhazSongNameFiles() {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open("song_name_khaz.txt")));

            String line = "";
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

        //      }
        return files;
    }
}
