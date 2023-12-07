package com.example.halla_navi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mapping extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View navi_menu;
    private View navi_find;

    private void startfind(){
        Intent intent = new Intent(this, navi_find.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapping);

        drawerLayout = (DrawerLayout) findViewById(R.id.map);
        navi_menu = (View) findViewById(R.id.nv_menu);
        navi_find = (View) findViewById(R.id.nv_find);

        Button menu = findViewById(R.id.menu);
        Button find = findViewById(R.id.find);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navi_menu);
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startfind();
            }
        });

    }
}