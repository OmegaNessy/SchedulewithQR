package com.example.schedulewithqr.model;

import android.database.Cursor;

import java.util.Arrays;
import java.util.List;

public class MonthMapper implements Mapper<Month>{

    private static final String MONTH_COLUMN = "month";
    private static final String DAYS_COLUMN = "days";
    private static final String ORDER_COLUMN = "week_number";

    public Month collectObject(Cursor cursor){
        Month month =new Month();
        String days  = cursor.getString(cursor.getColumnIndex(DAYS_COLUMN));
        List<String> strings = Arrays.asList( days.split(","));
        month.setDays(strings);
        String mnthName =cursor.getString(cursor.getColumnIndex(MONTH_COLUMN));
        month.setName(mnthName);
        int order = cursor.getInt(cursor.getColumnIndex(ORDER_COLUMN));
        month.setOrder(order);
        return month;
    }

}
