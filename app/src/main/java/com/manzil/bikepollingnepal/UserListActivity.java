package com.manzil.bikepollingnepal;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    LinearLayout container;
    DatabaseHelper databaseHelper;
//    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        databaseHelper = new DatabaseHelper(this);
        container = (LinearLayout) findViewById(R.id.container);
//        listView= (ListView) findViewById(R.id.listview);
        populateData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData() {
        ArrayList<UserInfo> list = databaseHelper.getUserList();
        container.removeAllViews();


        for (final UserInfo info : list
                ) {

//            TextView textView = new TextView(this);
//            textView.setBackgroundResource(R.drawable.bg_change);
//            textView.setTextSize(22);
//            textView.setText("Name:" + info.username + "Address:" + info.address + "Email:" + info.email + "Gender:" + info.gender + "Date of Birth:" + info.dob);


            View view = LayoutInflater.from(this).inflate(R.layout.view,null);

            TextView address= (TextView) view.findViewById(R.id.address);
            TextView username= (TextView) view.findViewById(R.id.username);
            ImageView imageView= (ImageView) view.findViewById(R.id.image);
//            if (info.image!=null)
//            imageView.setImageBitmap(RegisterActivity.getBitmap(info.image));
            username.setText(info.username);
            address.setText(info.address);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserListActivity.this,detailActivity.class);
                    intent.putExtra("id",info.id);
                    startActivity(intent);
                }
            });
            container.addView(view);
        }
    }
}
