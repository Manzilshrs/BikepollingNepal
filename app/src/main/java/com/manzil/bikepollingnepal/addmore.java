package com.manzil.bikepollingnepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Adapter;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class addmore extends AppCompatActivity {

   List<String> locationlist;

    ArrayAdapter<String> adapter;

    EditText nearbylocation;
    Button addbtn, confirm;

    ListView lv;

    String posturl =Constants.BASE_URL+"insertRider.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmore);

        nearbylocation = (EditText) findViewById(R.id.nearbyLocation);
        addbtn = (Button) findViewById(R.id.addbtn);
        confirm = (Button) findViewById(R.id.confirm);
        lv = (ListView) findViewById(R.id.locationListview);

        locationlist = new ArrayList<>();

        adapter = new ArrayAdapter<String>(addmore.this, android.R.layout.simple_list_item_multiple_choice, locationlist);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationlist.add(nearbylocation.getText().toString());
                nearbylocation.setText("");
                adapter.notifyDataSetChanged();
            }

        });
        nearbylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addmore.this, " Please add all the route location as possible :)", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(addmore.this, "Select and Long press to delete location", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                SparseBooleanArray checkedposition = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for (int i = count - 1; i >= 0; i--) {
                    if (checkedposition.get(i)) {
                        adapter.remove(locationlist.get(i));
                        Toast.makeText(addmore.this, "Location deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                checkedposition.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        lv.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AQuery aquery = new AQuery(addmore.this);
       HashMap<String, String> param = new HashMap<String, String>();
        param.put("bikename", MainApplication.offerRide.getBikename());
        param.put("bikeno",  MainApplication.offerRide.getBikenumber());
        param.put("currentlocation",MainApplication.offerRide.getLocation());
                JSONArray arr    = new JSONArray(locationlist);
                Toast.makeText(addmore.this,arr+ "", Toast.LENGTH_SHORT).show();
         param.put("nearbylocation","asdfasdf,asdfasdf");
//                Log.d("search",arr);

       //  param.put("nearbylocation",arr.toString());
        param.put("destination",MainApplication.offerRide.getDestination());
        param.put("time", MainApplication.offerRide.getTime());
        param.put("cost", MainApplication.offerRide.getCost());
                Log.d("sjdsd", "onClick: arr" + arr);
                Log.d("ss", "onClick: " + param.toString());
        aquery.ajax(posturl, param, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("postresponse", url + "postresponse" + status.getMessage() + " " + object);
            }
        });
            }
        });

    }


    public void getCsvList(){

        String lisString = "";
        for (String item:locationlist
             ) {
            lisString = lisString+","+item;
        }
    }
}
