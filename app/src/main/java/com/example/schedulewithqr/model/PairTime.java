package com.example.schedulewithqr.model;

public enum PairTime {
    FIRST("08:00","09:35","09:36","09:54","08:00,09:35","09:55,11:30"),
    SECOND("09:55","11:30","11:31","11:39","09:55,11:30","11:40,13:15"),
    THIRD("11:40","13:15","13:16","13:24","11:40,13:15","13:25,15:00"),
    FOURTH("13:25","15:00","15:01","15:09","13:25,15:00","15:10,16:46"),
    FIFTH("15:10","16:45","16:46","07:59","15:10,16:46","08:00,09:35");

    private String start;
    private String finish;
    private String breakStart;
    private String breakFinish;
    private String tableValue;
    private String nextPair;

    PairTime(String start, String finish, String breakStart, String breakFinish,
             String tableValue,String nextPair) {
        this.start = start;
        this.finish = finish;
        this.breakStart = breakStart;
        this.breakFinish = breakFinish;
        this.tableValue = tableValue;
        this.nextPair = nextPair;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getBreakStart() {
        return breakStart;
    }

    public String getBreakFinish() {
        return breakFinish;
    }

    public String getTableValue() {
        return tableValue;
    }

    public String getNextPair(){
        return nextPair;
    }
}
