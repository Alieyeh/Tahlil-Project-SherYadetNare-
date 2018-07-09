package com.example.alieyeh.appy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GameDbHelper extends SQLiteOpenHelper {

    private static final String Database_Name = "myQuiz.db";
    private static final int Database_version = 1;
    private SQLiteDatabase db;
    public static int songsSize;


    public GameDbHelper(Context context) {
        super(context, Database_Name, null, Database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        //////////////////////////////
        final String CREATE_TITRAJ_TABLE = "CREATE TABLE TITRAJMUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
        db.execSQL(CREATE_TITRAJ_TABLE);
        final String CREATE_POP_TABLE = "CREATE TABLE POPMUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
        db.execSQL(CREATE_POP_TABLE);
        final String CREATE_SONATI_TABLE = "CREATE TABLE SONATIMUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
        db.execSQL(CREATE_SONATI_TABLE);
        final String CREATE_KHAZ_TABLE = "CREATE TABLE KHAZMUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
        db.execSQL(CREATE_KHAZ_TABLE);

        fillKhazGamesTable();
        fillPopGamesTable();
        fillSonatiGamesTable();
        fillTitrajGamesTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS KHAZMUSICINFO");
        db.execSQL("DROP TABLE IF EXISTS POPMUSICINFO");
        db.execSQL("DROP TABLE IF EXISTS SONATIMUSICINFO");
        db.execSQL("DROP TABLE IF EXISTS TITRAJMUSICINFO");
        onCreate(db);
    }


    public void fillTitrajGamesTable() {

        ContentValues values = new ContentValues();
        FileManager fileManager = new FileManager();
        for (int i = 0; i < fileManager.titrajCount; i++) {
            values.put("LYRICS", String.valueOf(fileManager.listTitrajTextFiles().get(i)));
            values.put("SINGER", String.valueOf(fileManager.listTitrajSingerNameFiles().get(i)));
            values.put("SONGNAME", String.valueOf(fileManager.listTitrajSongNameFiles().get(i)));
            values.put("SONGS", fileManager.listTitrajSoundFilesBlob().get(i + fileManager.popCount + fileManager.khazCount + fileManager.sonatiCount));

            db.insert("TITRAJMUSICINFO", null, values);
        }
    }

    public void fillPopGamesTable() {

        ContentValues values = new ContentValues();
        FileManager fileManager = new FileManager();
        for (int i = 0; i < fileManager.popCount; i++) {
            values.put("LYRICS", String.valueOf(fileManager.listPopTextFiles().get(i)));
            values.put("SINGER", String.valueOf(fileManager.listPopSingerNameFiles().get(i)));
            values.put("SONGNAME", String.valueOf(fileManager.listPopSongNameFiles().get(i)));
            values.put("SONGS", fileManager.listPopSoundFilesBlob().get(i + fileManager.khazCount));
            db.insert("POPMUSICINFO", null, values);
        }
    }

    public void fillSonatiGamesTable() {

        ContentValues values = new ContentValues();
        FileManager fileManager = new FileManager();
        for (int i = 0; i <  fileManager.sonatiCount; i++) {
            values.put("LYRICS", String.valueOf(fileManager.listSonatiTextFiles().get(i)));
            values.put("SINGER", String.valueOf(fileManager.listSonatiSingerNameFiles().get(i)));
            values.put("SONGNAME", String.valueOf(fileManager.listSonatiSongNameFiles().get(i)));
            values.put("SONGS", fileManager.listSonatiSoundFilesBlob().get(i+fileManager.popCount + fileManager.khazCount));

            db.insert("SONATIMUSICINFO", null, values);
        }
    }

    public void fillKhazGamesTable() {

        ContentValues values = new ContentValues();
        FileManager fileManager = new FileManager();
        for (int i = 0; i < fileManager.khazCount; i++) {
            values.put("LYRICS", String.valueOf(fileManager.listKhazTextFiles().get(i)));
            values.put("SINGER", String.valueOf(fileManager.listKhazSingerNameFiles().get(i)));
            values.put("SONGNAME", String.valueOf(fileManager.listKhazSongNameFiles().get(i)));
            values.put("SONGS", fileManager.listKhazSoundFilesBlob().get(i));

            db.insert("KHAZMUSICINFO", null, values);
        }
    }

    public String giveTheLyric(int randomRow, int genre) {
        String lyricjumble = "";
        db = getReadableDatabase();
        switch (genre) {
            case 1:
                Cursor khazCursor = db.rawQuery("SELECT LYRICS FROM KHAZMUSICINFO WHERE ID=" + randomRow, null);
                if (khazCursor.moveToFirst()) {
                    lyricjumble = khazCursor.getString(khazCursor.getColumnIndex("LYRICS"));
                }
                khazCursor.close();
                break;
            case 2:
                Cursor popCursor = db.rawQuery("SELECT LYRICS FROM POPMUSICINFO WHERE ID=" + randomRow, null);
                if (popCursor.moveToFirst()) {
                    lyricjumble = popCursor.getString(popCursor.getColumnIndex("LYRICS"));
                }
                popCursor.close();
                break;
            case 3:
                Cursor sonaticursor = db.rawQuery("SELECT LYRICS FROM SONATIMUSICINFO WHERE ID=" + randomRow, null);
                if (sonaticursor.moveToFirst()) {
                    lyricjumble = sonaticursor.getString(sonaticursor.getColumnIndex("LYRICS"));
                }
                sonaticursor.close();
                break;
            case 4:
                Cursor titrajcursor = db.rawQuery("SELECT LYRICS FROM TITRAJMUSICINFO WHERE ID=" + randomRow, null);
                if (titrajcursor.moveToFirst()) {
                    lyricjumble = titrajcursor.getString(titrajcursor.getColumnIndex("LYRICS"));
                }
                titrajcursor.close();

                break;


        }

        return lyricjumble;

    }

    public byte[] giveTheSong(int randomRow, int genre) {
        byte[] musicbytes = null;
        db = getReadableDatabase();
        switch (genre) {
            case 1:
                Cursor khazCursor = db.rawQuery("SELECT SONGS FROM KHAZMUSICINFO WHERE ID=" + randomRow, null);
                if (khazCursor.moveToNext()) {
                    musicbytes = khazCursor.getBlob(0);
                }
                khazCursor.close();
                break;
            case 2:
                Cursor popCursor = db.rawQuery("SELECT SONGS FROM POPMUSICINFO WHERE ID=" + randomRow, null);
                if (popCursor.moveToNext()) {
                    musicbytes = popCursor.getBlob(0);
                }
                popCursor.close();
                break;
            case 3:
                Cursor sonatiCursor = db.rawQuery("SELECT SONGS FROM SONATIMUSICINFO WHERE ID=" + randomRow, null);
                if (sonatiCursor.moveToNext()) {
                    musicbytes = sonatiCursor.getBlob(0);
                }
                sonatiCursor.close();
                break;
            case 4:
                Cursor titrajCursor = db.rawQuery("SELECT SONGS FROM TITRAJMUSICINFO WHERE ID=" + randomRow, null);
                if (titrajCursor.moveToNext()) {
                    musicbytes = titrajCursor.getBlob(0);
                }
                titrajCursor.close();
                break;


        }


        return musicbytes;
    }


    public String giveTheSinger(int randomRow, int genre) {
        String singer = "";
        db = getReadableDatabase();

        switch (genre) {
            case 1:
                Cursor khazCursor = db.rawQuery("SELECT SINGER FROM KHAZMUSICINFO WHERE ID=" + randomRow, null);
                if (khazCursor.moveToFirst()) {
                    singer = khazCursor.getString(khazCursor.getColumnIndex("SINGER"));
                }
                khazCursor.close();
                break;
            case 2:
                Cursor popCursor = db.rawQuery("SELECT SINGER FROM POPMUSICINFO WHERE ID=" + randomRow, null);
                if (popCursor.moveToFirst()) {
                    singer = popCursor.getString(popCursor.getColumnIndex("SINGER"));
                }
                popCursor.close();
                break;
            case 3:
                Cursor sonatiCursor = db.rawQuery("SELECT SINGER FROM SONATIMUSICINFO WHERE ID=" + randomRow, null);
                if (sonatiCursor.moveToFirst()) {
                    singer = sonatiCursor.getString(sonatiCursor.getColumnIndex("SINGER"));
                }
                sonatiCursor.close();
                break;
            case 4:
                Cursor titrajCursor = db.rawQuery("SELECT SINGER FROM TITRAJMUSICINFO WHERE ID=" + randomRow, null);
                if (titrajCursor.moveToFirst()) {
                    singer = titrajCursor.getString(titrajCursor.getColumnIndex("SINGER"));
                }
                titrajCursor.close();
                break;
        }
        return singer;
    }

    public String giveTheSongName(int randomRow, int genre) {
        String songName = "";
        db = getReadableDatabase();
        switch (genre) {
            case 1:
                Cursor khazCursor = db.rawQuery("SELECT SONGNAME FROM KHAZMUSICINFO WHERE ID=" + randomRow, null);
                if (khazCursor.moveToFirst()) {
                    songName = khazCursor.getString(khazCursor.getColumnIndex("SONGNAME"));
                }
                khazCursor.close();
                break;
            case 2:
                Cursor popCursor = db.rawQuery("SELECT SONGNAME FROM POPMUSICINFO WHERE ID=" + randomRow, null);
                if (popCursor.moveToFirst()) {
                    songName = popCursor.getString(popCursor.getColumnIndex("SONGNAME"));
                }
                popCursor.close();
                break;
            case 3:
                Cursor sonatiCursor = db.rawQuery("SELECT SONGNAME FROM SONATIMUSICINFO WHERE ID=" + randomRow, null);
                if (sonatiCursor.moveToFirst()) {
                    songName = sonatiCursor.getString(sonatiCursor.getColumnIndex("SONGNAME"));
                }
                sonatiCursor.close();
                break;
            case 4:
                Cursor titrajCursor = db.rawQuery("SELECT SONGNAME FROM TITRAJMUSICINFO WHERE ID=" + randomRow, null);
                if (titrajCursor.moveToFirst()) {
                    songName = titrajCursor.getString(titrajCursor.getColumnIndex("SONGNAME"));
                }
                titrajCursor.close();
                break;

        }

        return songName;
    }

}



