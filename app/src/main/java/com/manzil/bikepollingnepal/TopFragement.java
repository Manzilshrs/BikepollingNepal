package com.manzil.bikepollingnepal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Manjil on 1/7/2018.
 */

public class TopFragement extends Fragment {

    EditText phone,password;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }
//
//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.homepage,null);
        phone= (EditText) view.findViewById(R.id.phone);
        password= (EditText) view.findViewById(R.id.password);
//String phoneValue=phone.getText().toString();
//String password=phone.getText().toString();


        return view;
    }

    public void setValue(String str){

        phone.setText(str);
//        password.setText(str);
    }
}
