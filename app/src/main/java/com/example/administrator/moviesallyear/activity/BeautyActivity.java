package com.example.administrator.moviesallyear.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.base.ToolbarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeautyActivity extends ToolbarActivity {
    public static final String TRANSIT_PIC = "picture";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivBeauty)
    ImageView ivBeauty;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_beauty;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        Glide.with(BeautyActivity.this).load(url).fitCenter().into(ivBeauty);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beauty_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId()==R.id.item_download)
        return super.onOptionsItemSelected(item);
    }
}
