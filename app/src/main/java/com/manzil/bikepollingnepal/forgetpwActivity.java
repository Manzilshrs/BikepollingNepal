package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgetpwActivity extends AppCompatActivity {

    EditText phoneno,email;
    Button confirm,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpw);
        phoneno= (EditText) findViewById(R.id.phoneno);
        email= (EditText) findViewById(R.id.email);
        confirm= (Button) findViewById(R.id.confirm);
        cancel= (Button) findViewById(R.id.cancel);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(forgetpwActivity.this,codeActivity.class);
                startActivity(intent);
            }
        });
    }
}
