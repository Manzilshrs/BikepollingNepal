package com.manzil.bikepollingnepal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {
    String id;
    DatabaseHelper databaseHelper;
    TextView username,email,phone,address,password,gender,dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        databaseHelper=new DatabaseHelper(this);
        id=getIntent().getStringExtra("id");

        username= (TextView) findViewById(R.id.username);
        email= (TextView) findViewById(R.id.email);
        phone= (TextView) findViewById(R.id.phone);
        address= (TextView) findViewById(R.id.address);
        password= (TextView) findViewById(R.id.password);
        gender= (TextView) findViewById(R.id.gender);
        dob= (TextView) findViewById(R.id.dob);
        populateData();

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(detailActivity.this,RegisterActivity.class);
               intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();

            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Delete User!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteUser(id);
                finish();

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void populateData(){
        UserInfo info=databaseHelper.getUserInfo(id);
        username.setText(info.username);
        email.setText(info.email);
        phone.setText(info.phone);
        address.setText(info.address);
        password.setText(info.password);
        gender.setText(info.gender);

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }
}
