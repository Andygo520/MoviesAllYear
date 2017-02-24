package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.fragment.CriticsFragment;
import com.example.administrator.moviesallyear.fragment.ExploreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private long time = 0;
    private String[] tabNames = {"影评", "发现"};
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
//        将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 双击返回桌面
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 2000)) {
                Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                //退出
//                AppExit.getInstance().exit();
//                finish();
//                System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new CriticsFragment();
                    break;
                case 1:
                    fragment = new ExploreFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        //  用来显示Tab标题
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }
}
