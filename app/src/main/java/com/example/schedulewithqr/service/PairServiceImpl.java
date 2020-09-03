package com.example.schedulewithqr.service;

import android.content.Context;

import com.example.schedulewithqr.database.DatabaseAdapter;
import com.example.schedulewithqr.logic.PairLogic;
import com.example.schedulewithqr.logic.PairLogicImpl;
import com.example.schedulewithqr.logic.QueryLogic;
import com.example.schedulewithqr.logic.QueryLogicImpl;
import com.example.schedulewithqr.model.MonthMapper;
import com.example.schedulewithqr.model.Pair;
import com.example.schedulewithqr.model.PairMapper;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PairServiceImpl implements PairService {

    private DatabaseAdapter adapter;
    private PairMapper pairMapper;
    private MonthMapper monthMapper;
    private QueryLogic queryLogic;
    private PairLogic pairLogic;
    private Integer building;
    private Integer room;
    private String day;

    public PairServiceImpl(Context context) {
        adapter = new DatabaseAdapter(context);
        pairMapper = new PairMapper();
        queryLogic = new QueryLogicImpl();

        monthMapper = new MonthMapper();
        pairLogic = new PairLogicImpl(adapter, monthMapper);
    }

    public Pair getPair(String myResult) throws JSONException {
        Map<String, String> params = queryLogic.createQueryParams(myResult);
        building = Integer.valueOf(params.get("building"));
        room = Integer.valueOf(params.get("room"));
        day = params.get("day");
        List<Pair> pairs = adapter.getPair(pairMapper, building, room, params.get("time"), day);
        Pair currentPair = pairLogic.getCurrentPair(pairs);
        return currentPair;
    }

    @Override
    public List<String> getPairView(Context context, String qrResult) throws JSONException {
        Map<String, String> params = queryLogic.createQueryParams(qrResult);
        building = Integer.valueOf(params.get("building"));
        room = Integer.valueOf(params.get("room"));
        day = params.get("day");
        final List<Pair> pairs = adapter.getRoomPair(pairMapper, building, room, day);
        List<String> pairsOverview = new ArrayList<>();
        for (Pair pair : pairs) {
            pairsOverview.add(pair.toString());
        }
        return pairsOverview;
    }
}
