package com.example.schedulewithqr.logic;

import com.example.schedulewithqr.model.PairTime;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QueryLogicImpl implements QueryLogic {

    @Override
    public Map<String, String> createQueryParams(String qrValue) throws JSONException {
        JSONObject qrVal = new JSONObject(qrValue);
        Map<String, String> params = new HashMap<>();
        String building = qrVal.getString("building");
        String room = qrVal.getString("room");
        String day = TimeUtil.getDay();
        String time = TimeUtil.getPairTime();
        params.put("building",building);
        params.put("room",room);
        params.put("day",day);
        params.put("time",time);
        return params;
    }
}
