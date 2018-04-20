package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class codeActivity extends AppCompatActivity {
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        verify= (Button) findViewById(R.id.verfiy);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(codeActivity.this,newpwActivity.class);
                startActivity(intent);
            }
        });
    }
}
