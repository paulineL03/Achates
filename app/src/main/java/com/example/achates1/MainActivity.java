package com.example.achates1;

import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


    }
    home home= new home();
    menuNav menunav= new menuNav();
    achatesNav achatesNav=new achatesNav();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
                return true;

            case R.id.menuAct:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menunav).commit();
                return true;

            case R.id.achatesIcon:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, achatesNav).commit();
                return true;
        }
        return false;
    }

}