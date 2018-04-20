package com.manzil.bikepollingnepal;

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

public class BottomFragment extends Fragment {
    EditText phone,password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register,null);
        phone= (EditText) view.findViewById(R.id.phone);
        password= (EditText) view.findViewById(R.id.password);
        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (FragmentExample)getActivity()).setTopFragment(phone.getText().toString());
                ( (FragmentExample)getActivity()).setTopFragment(password.getText().toString());
            }
        });
        return  view;
    }

}
