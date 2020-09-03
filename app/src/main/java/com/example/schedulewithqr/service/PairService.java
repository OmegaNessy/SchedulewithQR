package com.example.schedulewithqr.service;

import android.content.Context;
import android.content.Intent;

import com.example.schedulewithqr.model.Pair;

import org.json.JSONException;

import java.util.List;

public interface PairService {
    Pair getPair(String myResult) throws JSONException;
    List<String> getPairView(Context context,String qrResult) throws JSONException;
}
