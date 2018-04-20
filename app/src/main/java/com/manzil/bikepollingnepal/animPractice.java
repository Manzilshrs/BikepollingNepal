package com.manzil.bikepollingnepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class animPractice extends AppCompatActivity {
RelativeLayout view1,view2,view3,view4;
    Animation alpha,scale,rotate,translate,combine,combine1,combine2,combine3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animpractice);
        view1= (RelativeLayout) findViewById(R.id.view1);
        view2= (RelativeLayout) findViewById(R.id.view2);
        view3= (RelativeLayout) findViewById(R.id.view3);
        view4= (RelativeLayout) findViewById(R.id.view4);
        alpha= AnimationUtils.loadAnimation(this,R.anim.alpha);
        scale= AnimationUtils.loadAnimation(this,R.anim.scale);
        rotate= AnimationUtils.loadAnimation(this,R.anim.rotate);
        translate= AnimationUtils.loadAnimation(this,R.anim.translate);
        combine= AnimationUtils.loadAnimation(this,R.anim.combine);
        combine1= AnimationUtils.loadAnimation(this,R.anim.combine1);
        combine2= AnimationUtils.loadAnimation(this,R.anim.combine2);
        combine3= AnimationUtils.loadAnimation(this,R.anim.combine3);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view4.startAnimation(combine3);
                view3.startAnimation(combine2);
                view2.startAnimation(combine1);
                view1.startAnimation(combine);
        }
        });


        combine.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view4.startAnimation(combine3);
                view3.startAnimation(combine2);
                view2.startAnimation(combine1);
                view1.startAnimation(combine);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
