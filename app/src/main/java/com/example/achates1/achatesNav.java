package com.example.achates1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class achatesNav extends Fragment {

    public achatesNav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_achates_nav, container, false);
        TextView b=v.findViewById(R.id.textView13);
        TextView c=v.findViewById(R.id.textView14);
        View.OnClickListener listnr=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(getActivity(), accounts.class);
            startActivity(i);
            }
        };
        b.setOnClickListener(listnr);

        View.OnClickListener listnr2=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), device.class);
                startActivity(i);
            }
        };
        c.setOnClickListener(listnr2);
        return v;

    }

}