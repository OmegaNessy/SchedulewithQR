package com.example.schedulewithqr.model;

import android.database.Cursor;

public class PairMapper {

    private static final String COLUMN_ROOM = "room";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_PAIR = "lesson";
    private static final String COLUMN_TEACHER = "teacher";
    private static final String COLUMN_GROUP = "group";
    private static final String COLUMN_WEEK = "week_number";

    public Pair collectPair(Cursor cursor){
        Pair pair =new Pair();
        int room = cursor.getInt(cursor.getColumnIndex(COLUMN_ROOM));
        pair.setRoom(room);
        String day = cursor.getString(cursor.getColumnIndex(COLUMN_DAY));
        pair.setDay(day);
        int group = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP));
        pair.setGroup(group);
        String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
        pair.setTime(time);
        String pairName = cursor.getString(cursor.getColumnIndex(COLUMN_PAIR));
        pair.setPair(pairName);
        String taeacher = cursor.getString(cursor.getColumnIndex(COLUMN_TEACHER));
        pair.setTeacher(taeacher);
        int weekNumber = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEK));
        pair.setWeekNumber(weekNumber);
        return pair;
    }
}
