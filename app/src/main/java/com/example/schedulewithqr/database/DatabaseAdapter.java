package com.example.schedulewithqr.database;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.schedulewithqr.model.Mapper;
import com.example.schedulewithqr.model.Month;
import com.example.schedulewithqr.model.MonthMapper;
import com.example.schedulewithqr.model.Pair;
import com.example.schedulewithqr.model.PairMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static final String GET_PAIR = "select building.room,work_week.day,time.time,schedule.lesson,schedule.teacher,schedule.groupNum,schedule.week_num\n" +
            "from schedule \n" +
            "JOIN building on building.id==schedule.place\n" +
            "and building.building==? and building.room==?\n" +
            "join time on time.id==schedule.time and time.time==?\n" +
            "JOIN work_week on work_week.id == schedule.day and work_week.day==?";
    private static final String GET_ROOM_PAIRS = "select building.room,work_week.day,time.time,schedule.lesson,schedule.teacher,schedule.groupNum,schedule.week_num\n" +
            "from schedule \n" +
            "JOIN building on building.id==schedule.place\n" +
            "and building.building==? and building.room==?\n" +
            "join time on time.id==schedule.time\n" +
            "JOIN work_week on work_week.id == schedule.day and work_week.day==?";
    private static final String GET_MONTH = "SELECT * FROM calendar WHERE month ==?;";



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

    public List<Pair> getPair(PairMapper pairMapper, int building, int room,String time, String day) {
        String[] params = new String[]{String.valueOf(building), String.valueOf(room), time, day};
        List<Pair> pairs = new ArrayList<>();
        List<Object> objs = handleRawData(params, pairMapper, GET_PAIR);
        for (Object obj :objs) {
            pairs.add((Pair) obj);
        }
        return pairs;
    }

    public List<Pair> getRoomPair(Mapper pairMapper, int building, int room, String day) {
        List<Pair> pairs = new ArrayList<>();
        String[] params = new String[]{String.valueOf(building), String.valueOf(room), day};
         List<Object> objs = handleRawData(params, pairMapper, GET_ROOM_PAIRS);
        for (Object obj :objs) {
            pairs.add((Pair) obj);
        }
        return pairs;
    }

    public List<Month> getMonth(Mapper monthMapper, String month) {
        String[] params = new String[]{month};
        List<Month> months = new ArrayList<>();
        List<Object> objs = handleRawData(params, monthMapper, GET_MONTH);
        for (Object obj :objs) {
                months.add((Month)obj);
        }
        return months;
    }

    private List<Object> handleRawData(String[] params, Mapper mapper, String query) {
        open();
        ArrayList<Object> objs = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, params);
        if (cursor.moveToFirst()) {
            do {
                Object pair = mapper.collectObject(cursor);
                objs.add(pair);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return objs;
    }

}