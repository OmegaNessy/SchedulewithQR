package com.example.schedulewithqr;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_single_fragment);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, getFragment())
                    .commit();
        }
    }

    protected abstract AuthFragment getFragment();

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount()==1){
            finish();
        }else{
            fragmentManager.popBackStack();
        }
    }
}
