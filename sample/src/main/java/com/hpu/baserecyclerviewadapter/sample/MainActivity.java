package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleFragment singleFragment = new SingleFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, singleFragment)
                        .commit();
            }
        });
        findViewById(R.id.multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFragment multiFragment = new MultiFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, multiFragment)
                        .commit();
            }
        });
        findViewById(R.id.multi_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiTypeFragment multiTypeFragment = new MultiTypeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, multiTypeFragment)
                        .commit();
            }
        });
        findViewById(R.id.glid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlidFragment fragment = new GlidFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
            }
        });
        findViewById(R.id.stag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaggeredFragment fragment = new StaggeredFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
            }
        });
        SingleFragment singleFragment = new SingleFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, singleFragment)
                .commit();
    }
}
