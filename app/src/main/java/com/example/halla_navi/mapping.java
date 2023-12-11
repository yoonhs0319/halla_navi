package com.example.halla_navi;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class mapping extends AppCompatActivity {

    private static final int MIN_SWIPE_DISTANCE = 1000;
    private GestureDetector gestureDetector;
    private FrameLayout b_nv_frame;
    private BottomNavigationView b_nv;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private star star;
    private history history;
    private location location;
    private long back_time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapping);

        b_nv_frame = findViewById(R.id.b_nv_frame);
        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
                if (distanceY < -MIN_SWIPE_DISTANCE) {
                    ViewGroup.LayoutParams layoutParams = b_nv_frame.getLayoutParams();
                    layoutParams.height += 100;
                    b_nv_frame.setLayoutParams(layoutParams);
                    return true;
                } else if (distanceY < MIN_SWIPE_DISTANCE) {
                    ViewGroup.LayoutParams layoutParams = b_nv_frame.getLayoutParams();
                    layoutParams.height -= 100;
                    b_nv_frame.setLayoutParams(layoutParams);
                    return true;
                }
                return false;
            }
        });

        b_nv_frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

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
        setFrag(0);

        ImageButton find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startfind();
            }
        });

    }

    @Override
    public void onBackPressed() {
        long curtime = System.currentTimeMillis();
        long gaptime = curtime - back_time;

        if(0 <= gaptime && 2000 >= gaptime){
            System.exit(0);
        } else if (back_time < 0) {
            super.onBackPressed();
        } else{
            back_time = curtime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
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
                ft.replace(R.id.b_nv_frame,star);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.b_nv_frame,location);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.b_nv_frame,history);
                ft.commit();
                break;
        }
    }
}