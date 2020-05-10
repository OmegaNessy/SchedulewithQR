package com.example.schedulewithqr.model;

import java.util.List;

public class Month {
    private String name;
    private List<String> days;
    private int order;

    public Month() {
    }

    public Month(String name, List<String> days, int order) {
        this.name = name;
        this.days = days;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
