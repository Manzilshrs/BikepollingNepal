package com.manzil.bikepollingnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//import static com.manzil.bikepollingnepal.R.id.radiogrp;
//import static com.manzil.bikepollingnepal.R.id.ridchkoption;

public class LoginActivity extends AppCompatActivity {
    EditText phoneno, password;
    CheckBox rememberme;
    Button login, register;
    TextView forgetpw;
    RadioButton rider, customer;
    RadioGroup radiogrp;
    DatabaseHelper databaseHelper;
    Spinner spinner;
    AQuery aquery;
    String phone, pw, selected;
//    ArrayAdapter<String> adapter;
//    String names[] = {"Rider", "Customer"};


    String loginurl = Constants.BASE_URL+"login.php";

//    public void Selected(View view) {
//
//        switch (view.getId()) {
//
//
//            case ridchkoption:
//                login.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(LoginActivity.this, "Welcome to Rider Page", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
//                        startActivity(intent);
//                    }
//
//                });
//                break;
//
//            case R.id.cuschkoption:
//                login.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(LoginActivity.this, "Welcome to Customer Page", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, customerActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                break;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        databaseHelper = new DatabaseHelper(this);
        phoneno = (EditText) findViewById(R.id.PhoneNo);
        password = (EditText) findViewById(R.id.password);
//        rider = (RadioButton) findViewById(ridchkoption);
//        customer = (RadioButton) findViewById(R.id.cuschkoption);
        rememberme = (CheckBox) findViewById(R.id.remchkoption);
        login = (Button) findViewById(R.id.loginbtn);
        register = (Button) findViewById(R.id.signupbtn);
        forgetpw = (TextView) findViewById(R.id.forgetpw);
//        radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        aquery = new AQuery(this);
//        final String selected = new String();

//
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spinner.setAdapter(new UserSpinnerAdapter(this, databaseHelper.getUserList()));
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                UserInfo info = (UserInfo) spinner.getSelectedItem();
//                phoneno.setText(info.phone);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                phone = phoneno.getText().toString();
                pw = password.getText().toString();
                Toast.makeText(LoginActivity.this, selected + "", Toast.LENGTH_SHORT).show();
                verifyUser();

//                String phonenoValue = phoneno.getText().toString();
//                String passwordValue = password.getText().toString();
//
//                if (databaseHelper.isLoginValid(phonenoValue, passwordValue)) {
//                    startActivity(new Intent(LoginActivity.this, UserListViewActivity.class));
//                    Toast.makeText(LoginActivity.this, "Your Phone Number is:" + phonenoValue + " Please selected above option to Login", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(LoginActivity.this, "Login sucessful", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Intent intend = new Intent(LoginActivity.this, RegisterActivity.class);
//                    startActivity(intend);
//                    Toast.makeText(LoginActivity.this, "Login failure...Plz register to Login", Toast.LENGTH_SHORT).show();
//                }

            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        forgetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, forgetpwActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.blue:
                startActivity(new Intent(this, blueActivity.class));
                Toast.makeText(this, "This is Blue page ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red:
                startActivity(new Intent(this, redActivity.class));
                Toast.makeText(this, "This is Red page ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.green:
                startActivity(new Intent(this, greenActivity.class));
                Toast.makeText(this, "This is Green page ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yellow:
                startActivity(new Intent(this, yellowActivity.class));
                Toast.makeText(this, "This is Yellow page .Touch screen to change color", Toast.LENGTH_SHORT).show();
                break;
            case R.id.black:
                startActivity(new Intent(this, blackActivity.class));
                Toast.makeText(this, "This is Black page ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.animation:
                startActivity(new Intent(this, animPractice.class));
                Toast.makeText(this, "Please Touch Top Square Box For Movement...", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //
    public void verifyUser() {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("phone", phone);
        param.put("password", pw);

        aquery.ajax(loginurl, param, String.class, new AjaxCallback<String>() {

            // aquery.ajax(loginurl,param,JSONArray.class, new AjaxCallback<JSONArray>(){

            @Override
            public void callback(String url, String s, AjaxStatus status) {
                super.callback(url, s, status);
                Log.i("response", url + "response:" + s);
                Toast.makeText(LoginActivity.this, s + "", Toast.LENGTH_SHORT).show();


                UserInfo userInfo = new UserInfo();
                try {
                    JSONObject object = new JSONObject(s);
                    boolean responseStatus = object.optBoolean("status");

                    String message = object.getString("message");
//                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();


                    if (responseStatus) {
                        JSONObject userObj = object.getJSONObject("user");
                        userInfo.id = userObj.getString("id");
                        userInfo.username = userObj.getString("username");
                        userInfo.phone = userObj.getString("phone");
//                    userInfo.usertype=userObj.getString("usertype");
                        userInfo.email = userObj.getString("email");

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        if (selected.equals("Rider")) {
                            Toast.makeText(LoginActivity.this, "Welcome to Rider Page", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
                            startActivity(intent);
                        } else if (selected.equals("Customer")) {
                            Toast.makeText(LoginActivity.this, "Welcome to Customer Page", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, customerActivity.class);
                            startActivity(intent);
                        }
//                        } else if (userSelected.equals("Student")) {


//
//


//                        Toast.makeText(LoginActivity.this, "Welcome to Rider Page", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
//                        startActivity(intent);
//                        } else {
//
//                            Toast.makeText(LoginActivity.this, "Welcome to Customer Page", Toast.LENGTH_SHORT).show();
//                            Intent intent1 = new Intent(LoginActivity.this, customerActivity.class);
//                            startActivity(intent1);


//                            Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
//                            startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

//                        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
//                        for (int i = 0; i < array.length(); i++) {
//                            try {
//                                JSONObject object = array.getJSONObject(i);
//                                UserInfo info = new UserInfo();
//                                info.id = object.getString("id");
//                                info.username = object.getString("username");
//                                info.email = object.getString("email");
//                                info.phone = object.getString("phone");
//                                info.address = object.getString("address");
//                                info.password = object.getString("password");
//                                info.gender = object.getString("gender");
//                                info.dob = object.getString("dob");
////                           info.image=object.getString("image");
//                                list.add(info);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
////                listView.setAdapter(new UserListAdapter(jsonParsingActivity.this,list));
//                    }
//                }
//        );
    }
}



