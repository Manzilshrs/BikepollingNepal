package com.manzil.bikepollingnepal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tabUsingFragment extends AppCompatActivity {
    TextView login, register;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_using_fragment);
        login = (TextView) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        login.setOnClickListener(tabclicklistener);
        register.setOnClickListener(tabclicklistener);
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TopFragement()).commit();


    }


    public View.OnClickListener tabclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.register) {
                viewPager.setCurrentItem(0);
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TopFragement()).commit();
            } else if (v.getId() == R.id.login) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new BottomFragment()).commit();
                viewPager.setCurrentItem(1);

            }
        }

    };

    public class ViewpagerAdapter extends FragmentPagerAdapter {

        public ViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return new TopFragement();
            }

            return new BottomFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}