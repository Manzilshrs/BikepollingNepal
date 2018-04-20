package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import static com.manzil.bikepollingnepal.R.id.date;
import static com.manzil.bikepollingnepal.R.id.gender;
import static com.manzil.bikepollingnepal.R.id.nearbyLocation;


public class OfferActivity extends AppCompatActivity {

    EditText bikename,bikeno,time,cost,currentlocation,nearbyLocation,destination;
    DatePicker date;
    AQuery aquery;
    String posturl ="http://192.168.43.123/BikePollingNepal/insertRider.php";
    Button addmore,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerinfo);
        addmore = (Button) findViewById(R.id.addmore);
        nearbyLocation=(EditText)findViewById(R.id.nearbyLocation);

        bikename = (EditText) findViewById(R.id.bikename);
        bikeno = (EditText) findViewById(R.id.bikeno);
        time = (EditText) findViewById(R.id.time);
        cost = (EditText) findViewById(R.id.cost);
        currentlocation = (EditText) findViewById(R.id.currentlocation);
        destination = (EditText) findViewById(R.id.destination);
        date = (DatePicker) findViewById(R.id.date);
        confirm = (Button) findViewById(R.id.confirm);
        aquery = new AQuery(this);

//        addmore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(OfferActivity.this, addmore.class);
//                startActivity(intent);
//
//            }
//        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
                Toast.makeText(OfferActivity.this, "sucessful", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void postData() {
        Toast.makeText(this, "posted", Toast.LENGTH_SHORT).show();
        String bikenameValue = bikename.getText().toString();
        String bikenoValue = bikeno.getText().toString();
        String timeValue = time.getText().toString();
        String costValue = cost.getText().toString();
        String currentlocationValue = currentlocation.getText().toString();
//        String nearbylocationValue=nearbyLocation.getText().toString();
        String destinationValue = destination.getText().toString();
//        RadioButton checkedRb = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
//        String genderValue = checkedRb.getText().toString();
        String dateValue = date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth();
        MainApplication.offerRide = new OfferRide(dateValue, bikenameValue,
                bikenoValue, timeValue, costValue, currentlocationValue, destinationValue, new ArrayList<String>());

        Intent intent = new Intent(OfferActivity.this, addmore.class);
        startActivity(intent);
    }
}
