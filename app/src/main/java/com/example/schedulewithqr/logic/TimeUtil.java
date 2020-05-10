package com.example.schedulewithqr.logic;

import android.util.Log;

import com.example.schedulewithqr.model.Months;
import com.example.schedulewithqr.model.PairTime;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TimeUtil {

    private static final String DEFAULT = "08:00,9:25";

    public static String getDay() {
        LocalDate localDate
                = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        return dayOfWeek.toString().toLowerCase();
    }

    public static String getCurrentTime() {
        Calendar calendar = GregorianCalendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.HOUR_OF_DAY));
        stringBuilder.append(":");
        stringBuilder.append(calendar.get(Calendar.MINUTE));
        return stringBuilder.toString();
    }

    public static String getPairTime() {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        try {
            Date currentTime = parser.parse(TimeUtil.getCurrentTime());
            for (PairTime time : PairTime.values()) {
                Date startPair = parser.parse(time.getStart());
                Date finishPair = parser.parse(time.getFinish());
                Date startBrake = parser.parse(time.getBreakStart());
                Date finishBrake = parser.parse(time.getBreakFinish());
                if (currentTime.before(finishPair) && currentTime.after(startPair)) {
                    return time.getTableValue();
                } else if (currentTime.before(finishBrake) && currentTime.after(startBrake)) {
                    return time.getNextPair();
                }
            }
        } catch (ParseException e) {
            Log.d("Smth wrong with time in your device", e.getMessage());
        }
        return DEFAULT;
    }


    public static Map<String, Object> getCurrentDate() {
        Map<String,Object> params = new HashMap<>();
        Calendar calendar = GregorianCalendar.getInstance();
        String month = getMonthForInt(calendar.get(Calendar.MONTH));
        for (Months mnth : Months.values()) {
            if (mnth.getEnMonth().equals(month) ||
                    mnth.getRuMonth().equals(month)){
                    Integer day = Integer.valueOf(calendar.DAY_OF_MONTH);
                    params.put("mnth",mnth.getEnMonth());
                    params.put("day", day);
                    return params;
            }
        }
        return params;
    }

    private static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

}
