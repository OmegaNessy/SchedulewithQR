package com.example.schedulewithqr;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schedulewithqr.service.PairService;
import com.example.schedulewithqr.service.PairServiceImpl;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private PairService pairService;
    private String qrResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        pairService = new PairServiceImpl(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ListView listView = findViewById(R.id.listView);
        List<String> pairsOverview = new ArrayList<>();
        initVariables();
        try {
            pairsOverview = pairService.getPairView(this, qrResult);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, pairsOverview);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initVariables() {
        Bundle arguments = getIntent().getExtras();
        qrResult = arguments.getString("qrResult");
    }
}
