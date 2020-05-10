package com.example.schedulewithqr.model;

public enum Months {
    JANUARY ("january","января"),
    FEBRUARY ("february","февраля"),
    MARCH ("march","марта"),
    APRIL("april","апреля"),
    MAY("may","мая"),
    JUNE ("jun","июня"),
    JULY ("july","июля"),
    AUGUST("august","августа"),
    SEPTEMBER("september","сентября"),
    OCTOBER ("october","октября"),
    NOVEMBER ("november","ноября"),
    DECEMBER ("december","декабря");
    private String ruMonth;
    private String enMonth;

    Months(String ruMonth, String enMonth) {
        this.ruMonth = ruMonth;
        this.enMonth = enMonth;
    }

    public String getRuMonth() {
        return ruMonth;
    }

    public String getEnMonth() {
        return enMonth;
    }
}
