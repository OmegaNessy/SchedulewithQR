package com.example.schedulewithqr.logic;

import org.json.JSONException;

import java.util.Map;

public interface QueryLogic {
    Map<String,String> createQueryParams(String qrValue) throws JSONException;
}
