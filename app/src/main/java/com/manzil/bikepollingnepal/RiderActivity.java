package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RiderActivity extends AppCompatActivity {
    LinearLayout offer;
    LinearLayout status;
    View profile;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rider);
        offer= (LinearLayout) findViewById(R.id.offer);
        status= (LinearLayout) findViewById(R.id.status);
        profile=findViewById(R.id.profile);
        signout= (Button) findViewById(R.id.signout);

        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RiderActivity.this,OfferActivity.class);

                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RiderActivity.this,LoginActivity.class);
                Toast.makeText(RiderActivity.this, "Have a GoodDay :)", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
