package com.example.achates1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class menuNav extends Fragment {

    public menuNav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_menu_nav,container,false);
        Button button=(Button)v.findViewById(R.id.faceRecoBut);

        OnClickListener listnr=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), faceDetection.class);
                startActivity(intent);
            }
        };
        button.setOnClickListener(listnr);
        return v;
    }

}