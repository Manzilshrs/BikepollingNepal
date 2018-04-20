package com.manzil.bikepollingnepal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentExample extends AppCompatActivity {
    TopFragement topFragment;
    BottomFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
        topFragment = new TopFragement();
        bottomFragment = new BottomFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.topcontainer, topFragment);
        transaction.replace(R.id.bottomcontainer, bottomFragment);
        transaction.commit();

    }

    public void setTopFragment(String str) {

        topFragment.setValue(str);
    }
}
