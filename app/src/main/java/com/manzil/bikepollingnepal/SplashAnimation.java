package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAnimation extends AppCompatActivity {
    TextView welcome;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_animation);
        welcome= (TextView) findViewById(R.id.welcome);
        logo= (ImageView) findViewById(R.id.logo);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.transition);
        welcome.startAnimation(myanim);
        logo.startAnimation(myanim);
        myanim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashAnimation.this,LoginActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
