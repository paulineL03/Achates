package com.example.achates1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class home extends Fragment {

    public home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View v = inflater.inflate(R.layout.fragment_home,container,false);
        Button button=(Button)v.findViewById(R.id.gbutton);

        OnClickListener listnr=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), menu.class);
                startActivity(intent);
            }
        };
        button.setOnClickListener(listnr);
        return v;

    }



}