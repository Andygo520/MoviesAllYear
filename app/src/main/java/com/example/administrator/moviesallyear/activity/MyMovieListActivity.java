package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.base.ToolbarActivity;
import com.example.administrator.moviesallyear.fragment.MoviesWannaWatchFragment;
import com.example.administrator.moviesallyear.fragment.MoviesWatchedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMovieListActivity extends ToolbarActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private MoviesWannaWatchFragment wannaWatchFragment;//想看
    private MoviesWatchedFragment watchedFragment;//看过
    private String[] titles = {"想看", "看过"};

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_movie_list;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (wannaWatchFragment == null)
            wannaWatchFragment = new MoviesWannaWatchFragment();
        if (watchedFragment == null)
            watchedFragment = new MoviesWatchedFragment();
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_wanna_watch,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_add){
            startActivity(new Intent(MyMovieListActivity.this,AddMoviesWannaWatchActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return wannaWatchFragment;
            else
                return watchedFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return titles[0];
            else
                return titles[1];
        }
    }
}
