package com.manzil.bikepollingnepal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manjil on 12/28/2017.
 */

public class UserListAdapter extends ArrayAdapter<UserInfo> {
    Context contex;
    public UserListAdapter(@NonNull Context context ,ArrayList<UserInfo>list) {
        super(context, 0,list);

        this.contex=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(contex).inflate(R.layout.view,null);

        TextView address= (TextView) view.findViewById(R.id.address);
        TextView username= (TextView) view.findViewById(R.id.username);
        ImageView imageView= (ImageView) view.findViewById(R.id.image);

        UserInfo info= getItem(position);
        if (info.image!=null)
            imageView.setImageBitmap(RegisterActivity.getBitmap(info.image));
        username.setText(info.username);
        address.setText(info.address);

        final UserInfo finalInfo = info;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contex,detailActivity.class);
                intent.putExtra("id", finalInfo.id);
                contex.startActivity(intent);
            }
        });
        return view;
    }
}
