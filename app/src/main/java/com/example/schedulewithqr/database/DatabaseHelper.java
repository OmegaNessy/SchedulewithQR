package com.example.schedulewithqr.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH;
    public static String DB_NAME = "schedule1.db";
    public static final int SCHEMA = 1;
    public Context context;

    public static final String TABLE_SCHEDULE="schedule";
    public static final String KEY_ID = "_id";
    public static final String KEY_GROUP = "groupNum";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_PLACE = "place";
    public static final String KEY_TIME = "time";
    public static final String KEY_DAY = "day";
    public static final String KEY_LESSON = "lesson";
    public static final String KEY_WEEKNUM = "week_num";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.context = context;
        DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                myInput = context.getAssets().open(DB_NAME);
                String outFileName = DB_PATH;
                myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException ex) {
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }
}