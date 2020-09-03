package com.example.schedulewithqr.logic;

import android.content.Context;

import com.example.schedulewithqr.database.DatabaseAdapter;
import com.example.schedulewithqr.model.Month;
import com.example.schedulewithqr.model.MonthMapper;
import com.example.schedulewithqr.model.Pair;

import java.util.List;
import java.util.Map;

public class PairLogicImpl implements PairLogic {

    private DatabaseAdapter adapter;
    private MonthMapper monthMapper;

    public PairLogicImpl(DatabaseAdapter adapter, MonthMapper monthMapper) {
        this.adapter = adapter;
        this.monthMapper = monthMapper;
    }

    @Override
    public Pair getCurrentPair(List<Pair> pairs) {
        if (pairs.size() == 1) {
            return pairs.get(0);
        }
        if (pairs.size() == 0) {
            return new Pair();
        }
        if (pairs.size() == 2) {
            Map<String, Object> params = TimeUtil.getCurrentDate();
            Integer day = (Integer) params.get("day");
            List<Month> monthes = adapter.getMonth(monthMapper, (String) params.get("mnth"));
            for (Month mnth: monthes){
                if (mnth.getDays().contains(day)) {
                    if (mnth.getOrder() == pairs.get(0).getWeekNumber()){
                        return pairs.get(0);
                    }
                    return pairs.get(1);
                }
            }
        }
        return null;
    }
}
