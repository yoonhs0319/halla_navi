package com.example.my_school_map;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        LayoutParams params = imageView.getLayoutParams();
        params.width = 100;
        params.height = 100;
        imageView.setLayoutParams(params);

    }
}