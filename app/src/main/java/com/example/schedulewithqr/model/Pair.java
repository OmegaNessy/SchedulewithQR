package com.example.schedulewithqr.model;

public class Pair {
    private int room;
    private String day;
    private String time;
    private String pair;
    private String teacher;
    private int group;
    private int weekNumber;

    public Pair(int room, String day, String time, String pair, String teacher, int group, int weekNumber) {
        this.room = room;
        this.day = day;
        this.time = time;
        this.pair = pair;
        this.teacher = teacher;
        this.group = group;
        this.weekNumber = weekNumber;
    }

    public Pair() {
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public boolean isEmpty() {
        if (room == 0 || day == null || time == null
                || pair == null || teacher == null || group == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Кабитнет: " + room + ";\n" +
                "Пара: " + pair + ";\n" +
                "Преподаватель: " + teacher + ";\n" +
                "Группа: " + group + ";\n" +
                "Время проведения: " + time + ".";
    }
}
