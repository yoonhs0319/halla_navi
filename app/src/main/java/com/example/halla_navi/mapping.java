package com.example.halla_navi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class mapping extends AppCompatActivity {

   private BottomNavigationView b_nv;
   private FragmentManager fm;
   private FragmentTransaction ft;
   private star star;
   private history history;
   private location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapping);
        b_nv = findViewById(R.id.b_nv);
        b_nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.star) {
                    setFrag(0);
                } else if (item.getItemId() == R.id.location) {
                    setFrag(1);
                } else if (item.getItemId() == R.id.history) {
                    setFrag(2);
                }
                return true;
            }
        });
        star = new star();
        history = new history();
        location = new location();

        ImageButton find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startfind();
            }
        });

    }

    private void startfind(){
        Intent intent = new Intent(this, navi_find.class);
        startActivity(intent);
    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.map,star);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.map,location);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.map,history);
                ft.commit();
                break;
        }
    }
}