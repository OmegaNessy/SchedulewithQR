package com.example.schedulewithqr.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.schedulewithqr.model.Month;
import com.example.schedulewithqr.model.MonthMapper;
import com.example.schedulewithqr.model.Pair;
import com.example.schedulewithqr.model.PairMapper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static final String GET_PAIR = "SELECT * FROM schedule WHERE building=? AND room=? AND time=?" +
            "AND day=?";
    private static final String GET_MONTH = "SELECT * FROM calendar WHERE month=?;";

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
        dbHelper.create_db();
    }

    private DatabaseAdapter open() {
        database = dbHelper.open();
        return this;
    }

    private void close() {
        dbHelper.close();
    }

    public List<Pair> getPair(PairMapper pairMapper, int building, int room, String time, String day) {
        ArrayList<Pair> pairs = new ArrayList<>();
        open();
        String[] params = new String[]{String.valueOf(building), String.valueOf(room), time, day};
        Cursor cursor = database.rawQuery(GET_PAIR, params);
        if (cursor.moveToFirst()) {
            do {
                Pair pair = pairMapper.collectPair(cursor);
                pairs.add(pair);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return pairs;
    }

    public List<Month> getMonth(MonthMapper monthMapper,String month){
        ArrayList<Month> months = new ArrayList<>();
        open();
        String[] params = new String[]{month};
        Cursor cursor = database.rawQuery(GET_MONTH, params);
        if (cursor.moveToFirst()) {
            do {
               Month mnth = monthMapper.collectMonth(cursor);
                months.add(mnth);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return months;
    }

}