package com.manzil.bikepollingnepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

public class UserListViewActivity extends AppCompatActivity {
    //ListView listView;
    GridView gridView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
//       listView= (ListView) findViewById(R.id.listview);
        gridView = (GridView) findViewById(R.id.gridview);
        databaseHelper = new DatabaseHelper(this);
        UserListAdapter adapter = new UserListAdapter(this, databaseHelper.getUserList());

//        listView.setAdapter(adapter);
        gridView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        UserListAdapter adapter = new UserListAdapter(this, databaseHelper.getUserList());

        gridView.setAdapter(adapter);
    }
}
