package com.example.achates1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class menuNav extends Fragment {

    public menuNav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_menu_nav,container,false);
        ImageView button=(android.widget.ImageView) v.findViewById(R.id.imageLabelBut);
        ImageView button2= (android.widget.ImageView) v.findViewById(R.id.homeAuto);
        ImageView button3= (android.widget.ImageView) v.findViewById(R.id.homeSecu);

        OnClickListener listnr=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), imageLabel.class);
                startActivity(intent);
            }
        };
        button.setOnClickListener(listnr);

        OnClickListener listnr2=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), homeAutomation.class);
                startActivity(intent);
            }
        };
        button2.setOnClickListener(listnr2);

        OnClickListener listnr3=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), homeSecu.class);
                startActivity(intent);
            }
        };
        button3.setOnClickListener(listnr3);
        return v;
    }

}