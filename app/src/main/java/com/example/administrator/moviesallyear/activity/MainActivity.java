package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MoviesAllYearApplication;
import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MovieCriticsDao;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.UrlHelper;
import model.Beauty;
import model.InTheaterMovie;
import model.MovieCritics;
import model.MovieItem;
import model.Result;
import model.Top250Adapter;

public class MainActivity extends AppCompatActivity {
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象
    private List<MovieItem> data;
    @BindView(R.id.picker)
    DiscreteScrollView picker;
    @BindView(R.id.tvCritics)
    TextView tvCritics;
    @BindView(R.id.tvComingMovies)
    TextView tvComingMovies;
    @BindView(R.id.tvTop250)
    TextView tvTop250;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.ivBeauty)
    ImageView ivBeauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        data = new ArrayList<>();
        //        获取MovieCriticsDao对象
        criticsDao = MoviesAllYearApplication.getInstances().getDaoSession().getMovieCriticsDao();
//        取出最新的一条影评
        MovieCritics critics = criticsDao.queryBuilder()
                .orderDesc(MovieCriticsDao.Properties.CreateTime)
                .list().get(0);
        String text = "我的影评\n " + critics.getName() + "\n" + critics.getCritics();
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new AbsoluteSizeSpan(50), 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        style.setSpan(new AbsoluteSizeSpan(40), 4, text.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#319BD9")), 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
        style.setSpan(new StyleSpan(Typeface.BOLD), 4, critics.getName().length() + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //粗体,空格跟换行都算一个字符，所以要加6
        tvCritics.setText(style);
        getData(UrlHelper.beauty_url);
        getData1(UrlHelper.in_theaters_url);
    }

    public void getData1(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Result result = JSON.parseObject(s, Result.class);
                        List<InTheaterMovie> list = JSON.parseArray(result.getSubjects().toString(), InTheaterMovie.class);

                        data.clear();
//                        只显示5条记录
                        for (int i = 0; i < 5; i++) {
                            String title = list.get(i).getTitle();
                            double rating = list.get(i).getRating().getAverage();
                            String imageUrl = list.get(i).getImages().getLarge();
                            Log.d("beautyurl", imageUrl);
                            data.add(new MovieItem(title, rating, imageUrl));
                        }
                        picker.setAdapter(new Top250Adapter(MainActivity.this, data, R.layout.item_250));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                queue.add(request);
            }
        }).start();
    }

    public void getData(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("beautyurl", s);
                        Beauty beauty = JSON.parseObject(s, Beauty.class);
                        List<Beauty.ResultsBean> beautyList = beauty.getResults();
                        String beautyUrl = beautyList.get(0).getUrl();//获取最新的干货福利
                        Log.d("beautyurl", beautyUrl);
                        Glide.with(MainActivity.this).load(beautyUrl).into(ivBeauty);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                queue.add(request);
            }
        }).start();
    }

    @OnClick({R.id.tvCritics, R.id.tvComingMovies, R.id.tvTop250, R.id.tvMore, R.id.ivBeauty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCritics:
                break;
            case R.id.tvComingMovies:
                break;
            case R.id.tvTop250:
                startActivity(new Intent(MainActivity.this, Top250Activity.class));
                finish();
                break;
            case R.id.tvMore:
                break;
            case R.id.ivBeauty:
                break;
        }
    }
}
