package com.manzil.bikepollingnepal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class UserSpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    public UserSpinnerAdapter(@NonNull Context context ,ArrayList<String>list) {
        super(context, 0,list);

        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      TextView view=new TextView(context);
        view.setText("Rider");
        view.setPadding(10,10,10,10);
        view.setTextColor(Color.WHITE);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view=new TextView(context);
        view.setText("Customer");
        view.setPadding(10,10,10,10);
        view.setTextColor(Color.WHITE);
        return view;    }
}
