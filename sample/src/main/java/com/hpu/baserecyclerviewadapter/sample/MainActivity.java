package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SingleFragment singleFragment = new SingleFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, singleFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.single:
                SingleFragment singleFragment = new SingleFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, singleFragment)
                        .commit();
                break;
            case R.id.multi:
                MultiTypeFragment multiTypeFragment = new MultiTypeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, multiTypeFragment)
                        .commit();
                break;
            case R.id.glid:
                GlidFragment glidFragment = new GlidFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, glidFragment)
                        .commit();
                break;
            case R.id.staggered:
                StaggeredFragment fragment = new StaggeredFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
