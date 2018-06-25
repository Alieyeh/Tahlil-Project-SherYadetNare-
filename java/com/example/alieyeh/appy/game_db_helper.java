package com.example.nazanin_sarrafzadeh.sheryadetnare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class game_db_helper extends SQLiteOpenHelper {

    private static final String Database_Name="myQuiz.db";
    private static final int Database_version=1;
    private SQLiteDatabase db;
    public static int songsSize;
    private MusicManager musicManager=new MusicManager();

    public game_db_helper(Context context) {
        super(context, Database_Name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        //////////////////////////////
        final String CREATE_TABLE="CREATE TABLE MUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
        db.execSQL(CREATE_TABLE);
        fillGamesTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS MUSICINFO");
        onCreate(db);
    }




    public void fillGamesTable(){
        List<String> singer=new ArrayList<>();
        List<String> songName=new ArrayList<>();

        singer.add("افشین");
        singer.add("فرامرز اصلانی");
        singer.add("علی زند وکیلی");
        singer.add("علی زند وکیلی");
        singer.add("اندی");
        singer.add("امیر عظیمی");
        singer.add("امیر عظیمی");
        singer.add("مرتضی");
        singer.add("شهرام ناظری");
        singer.add("گروه آرین");
        singer.add("مسیح و آرش");
        singer.add("داود بهبودی");
        singer.add("اشوان");
        singer.add("اشوان");
        singer.add("بابک جهانبخش");
        singer.add("بنان");
        singer.add("اندی");
        singer.add("سبامک عباسی");
        singer.add("ایرج بسطامی");
        singer.add("بهنام بانی");
        singer.add("بهنام صفوی");
        //    singers.add("مهران زاهدی");
        singer.add("ایرج بسطامی ");
        singer.add("مسیح و آرش");
        singer.add("شجریان");
        singer.add("فرهاد مهراد");
        singer.add("چارتار");
        singer.add("چارتار");
        singer.add("چارتار");
        singer.add("چارتار");
        singer.add("همایون شجریان");
        singer.add("علی زند وکیلی");
        singer.add("َشهرام ناظری");
        singer.add("ویگن");
        singer.add("همایون شجریان");
        singer.add("ابی");
        singer.add("ابی");
        singer.add("ابی");
        singer.add("احسان خواجه امیری");
        singer.add("احسان خواجه امیری");
        singer.add("بنان");
        singer.add("سیامک عباسی");
        singer.add("محمد اصفهانی");
        singer.add("فریدون آسرایی");
        singer.add("حامد همایون");
        singer.add("ناصر عبداللهی");
        singer.add("حجت اشرف زاده");
        singer.add("محمد علیزاده");
        singer.add("فرهاد مهراد");
        singer.add("شجریان");
        singer.add("محمد نوری");
        singer.add("فرزاد فرزین");
        singer.add("شهرام شب پره");
        singer.add("حجت اشرف زاده");
        singer.add("شارومین");
        singer.add("سیامک عباسی");
        singer.add("سیامک عباسی");
        singer.add("سیامک عباسی");
        singer.add("محسن یگانه");
        singer.add("محمد اصفهانی");
        singer.add(" th7 باند");

        songName.add("زمستون");
        songName.add("اگه یه روز");
        songName.add("آخرین رویا");
        songName.add("لالایی");
        songName.add("آمنه");
        songName.add("لالایی");
        songName.add("لیلی");
        songName.add("انار انار");
        songName.add("اندک اندک");
        songName.add("پرواز");
        songName.add("ارومه دل");
        songName.add("عسل");
        songName.add("بهت مریضم");
        songName.add("تنها شدم");
        songName.add("پری زاد");
        songName.add("تا بهار دلنشین");
        songName.add("بلا");
        songName.add("بارون");
        songName.add("بگو کجایی");
        songName.add("بسمه");
        songName.add("عشق من باش");
        songName.add("به رهی دیدم برگ خزان");
        songName.add("بیا بازم");
        songName.add("بوته چین");
        songName.add("بوی عیدی");
        songName.add("آشوبم");
        songName.add("باران تویی");
        songName.add("در حسرت ماه");
        songName.add("خوشا به من");
        songName.add("چونی بی من");
        songName.add("دامن کشان");
        songName.add("در هوایت بیقرارم روز و شب");
        songName.add("دل دیوانه");
        songName.add("دل یک دله کن");
        songName.add("عطر تو");
        songName.add("این آخرین باره");
        songName.add("کی اشکاتو پاک میکنه");
        songName.add("احساس آرامش");
        songName.add("میوه ی ممنوعه");
        songName.add("الهه ناز");
        songName.add("فلسفه پوچ");
        songName.add("غوغای ستارگان");
        songName.add("گل هباهو");
        songName.add("می روم");
        songName.add("هوای هوا");
        songName.add("ماه و ماهی");
        songName.add("خستم");
        songName.add("کوچ بنفشه ها");
        songName.add("مرغ سخر");
        songName.add("نازنین مریم");
        songName.add("اون تو نیستی");
        songName.add("پریا");
        songName.add("پرسون پرسون");
        songName.add("ساغی");
        songName.add("دلفریب");
        songName.add("من راهیم");
        songName.add("شبی خوش");
        songName.add("سکوت");
        songName.add("تو ای پری کجایی");
        songName.add("واست میمیرم");
        songsSize=singer.size();
        ContentValues values = new ContentValues();
        for (int i = 0; i <singer.size() ; i++) {
            values.put("SONGS",musicManager.listSoundFilesBlob().get(i));
            values.put("LYRICS", String.valueOf(musicManager.listTextFiles().get(i)));
            values.put("SINGER", singer.get(i));
            values.put("SONGNAME", songName.get(i));

            db.insert("MUSICINFO", null, values);
        }
    }

    public String giveTheLyric(int randomRow){
        String lyricjumble="";
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT LYRICS FROM MUSICINFO WHERE ID="+randomRow,null);
        if (cursor.moveToFirst()) {
            lyricjumble = cursor.getString(cursor.getColumnIndex("LYRICS"));
        }
        cursor.close();
        return lyricjumble;
    }

    public byte[] giveTheSong(int randomRow){
        byte[] musicbytes = null;
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SONGS FROM MUSICINFO WHERE ID="+randomRow,null);
        if (cursor.moveToNext()) {
            musicbytes = cursor.getBlob(0);
        }
        cursor.close();
        return musicbytes;
    }

    public String giveTheSinger(int randomRow){
        String singer="";
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SINGER FROM MUSICINFO WHERE ID="+randomRow,null);
        if (cursor.moveToFirst()) {
            singer = cursor.getString(cursor.getColumnIndex("SINGER"));
        }
        cursor.close();
//
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        FileDescriptor fd = mainActivity.context.getResources().openRawResourceFd(listRawMediaFiles().get(randomRow-1)).getFileDescriptor();
//        mmr.setDataSource(fd);
//        singer = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        return singer;
    }
    public String giveTheSongName(int randomRow){
        String songName="";
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SONGNAME FROM MUSICINFO WHERE ID="+randomRow,null);
        if (cursor.moveToFirst()) {
            songName = cursor.getString(cursor.getColumnIndex("SONGNAME"));
        }
        cursor.close();
//
//        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//        FileDescriptor fd = mainActivity.context.getResources().openRawResourceFd(listRawMediaFiles().get(randomRow-1)).getFileDescriptor();
//        mmr.setDataSource(fd);
//        singer = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        return songName;
    }



//    public ArrayList<byte[]> listSoundFilesBlob(){
//
//        ArrayList<byte[]> soundfiles=new ArrayList<>();
//        for (int i = 0; i <listRawMediaFiles().size() ; i++) {
//            InputStream istream =mainActivity.context.getResources().openRawResource(listRawMediaFiles().get(i));
//            try {
//                byte[] music = new byte[istream.available()];
//                istream.read(music);
//                soundfiles.add(music);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return soundfiles;
//    }
//
//    //returns id of songs in asset folder
//    public static List<Integer> listRawMediaFiles() {
//        List<Integer> ids = new ArrayList<>();
//        //what's your problem
//        for (Field field : R.raw.class.getFields()) {
//            try {
//                ids.add(field.getInt(field));
//            } catch (Exception e) {
//
//            }
//        }
//        return ids;
//    }
//
//    public ArrayList<String> listTextFiles(){
//        ArrayList<String> files=new ArrayList<>();
//        AssetManager am =mainActivity.context.getAssets();
//        String assetFileNames[] = new String[0];
//        try {
//            assetFileNames = am.list("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BufferedReader reader = null;
//        for(String assetFileName : assetFileNames) {
//            try {
//                reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open(assetFileName)));
//
//                String line="";
//                while ((line = reader.readLine()) != null) {
//
//                    files.add(line);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }
//        return files;
//    }

}


//
//public class game_db_helper extends SQLiteOpenHelper {
//
//    LyricsJumbleActivity mainActivity=new LyricsJumbleActivity();
//    private static final String Database_Name="myQuiz.db";
//    private static final int Database_version=1;
//    private SQLiteDatabase db;
//
//    public game_db_helper(Context context) {
//        super(context, Database_Name, null, Database_version);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        this.db=db;
//        final String CREATE_TABLE="CREATE TABLE MUSICINFO ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SONGS BLOB, LYRICS TEXT, SINGER TEXT , SONGNAME TEXT)";
//        db.execSQL(CREATE_TABLE);
//        fillGamesTable();
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        db.execSQL("DROP TABLE IF EXISTS MUSICINFO");
//        onCreate(db);
//    }
//
//
//
//
//    public void fillGamesTable(){
//        List<String> singer=new ArrayList<>();
//        List<String> songName=new ArrayList<>();
////
////        singer.add("eli"); singer.add("w");singer.add("cloud");
////        songName.add("yeek"); songName.add("do");songName.add("se");
//
//        ContentValues values = new ContentValues();
//        for (int i = 0; i <listTextFiles().size(); i++) {
//            values.put("SONGS",listSoundFilesBlob().get(i));
//            values.put("LYRICS", String.valueOf(listTextFiles().get(i)));
//   //         values.put("SINGER", singer.get(i));
//   //         values.put("SONGNAME", songName.get(i));
//
//            db.insert("MUSICINFO", null, values);
//        }
//    }
//
//    public String giveTheLyric(int randomRow){
//        String lyricjumble="";
//        db=getReadableDatabase();
//        Cursor cursor=db.rawQuery("SELECT LYRICS FROM MUSICINFO WHERE ID="+randomRow,null);
//        if (cursor.moveToFirst()) {
//            lyricjumble = cursor.getString(cursor.getColumnIndex("LYRICS"));
//        }
//        cursor.close();
//        return lyricjumble;
//    }
//
//    public byte[] giveTheSong(int randomRow){
//        byte[] musicbytes = null;
//        db=getReadableDatabase();
//        Cursor cursor=db.rawQuery("SELECT SONGS FROM MUSICINFO WHERE ID="+randomRow,null);
//        if (cursor.moveToNext()) {
//            musicbytes = cursor.getBlob(0);
//        }
//        cursor.close();
//        return musicbytes;
//    }
//
//    public String giveTheSinger(int randomRow){
//        String singer="";
//        db=getReadableDatabase();
//        Cursor cursor=db.rawQuery("SELECT SINGER FROM MUSICINFO WHERE ID="+randomRow,null);
//        if (cursor.moveToFirst()) {
//            singer = cursor.getString(cursor.getColumnIndex("SINGER"));
//        }
//        cursor.close();
////
////        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
////        FileDescriptor fd = mainActivity.context.getResources().openRawResourceFd(listRawMediaFiles().get(randomRow-1)).getFileDescriptor();
////        mmr.setDataSource(fd);
////        singer = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
//        return singer;
//    }
//    public String giveTheSongName(int randomRow){
//        String songName="";
//        db=getReadableDatabase();
//        Cursor cursor=db.rawQuery("SELECT SONGNAME FROM MUSICINFO WHERE ID="+randomRow,null);
//        if (cursor.moveToFirst()) {
//            songName = cursor.getString(cursor.getColumnIndex("SONGNAME"));
//        }
//        cursor.close();
//
//        return songName;
//    }
//
//
//
//    public ArrayList<byte[]> listSoundFilesBlob(){
//
//        ArrayList<byte[]> soundfiles=new ArrayList<>();
//        for (int i = 0; i <listRawMediaFiles().size() ; i++) {
//            InputStream istream =mainActivity.context.getResources().openRawResource(listRawMediaFiles().get(i));
//            try {
//                byte[] music = new byte[istream.available()];
//                istream.read(music);
//                soundfiles.add(music);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return soundfiles;
//    }
//
//    //returns id of songs in asset folder
//    public static List<Integer> listRawMediaFiles() {
//        List<Integer> ids = new ArrayList<>();
//        //what's your problem
//        for (Field field : R.raw.class.getFields()) {
//            try {
//                ids.add(field.getInt(field));
//            } catch (Exception e) {
//
//            }
//        }
//        return ids;
//    }
//
//    public ArrayList<String> listTextFiles(){
//        ArrayList<String> files=new ArrayList<>();
//        AssetManager am =mainActivity.context.getAssets();
//        String assetFileNames[] = new String[0];
//        try {
//            assetFileNames = am.list("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BufferedReader reader = null;
//        for(String assetFileName : assetFileNames) {
//            try {
//                reader = new BufferedReader(new InputStreamReader(mainActivity.context.getAssets().open(assetFileName)));
//
//                String line="";
//                while ((line = reader.readLine()) != null) {
//
//                    files.add(line);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }
//        return files;
//    }
//
//}
