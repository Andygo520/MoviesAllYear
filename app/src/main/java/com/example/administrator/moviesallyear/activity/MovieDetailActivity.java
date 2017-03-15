package com.example.administrator.moviesallyear.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.UrlHelper;
import model.MovieItem;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    private Context context;
    private String title, itemId, imageUrl;// 电影名,电影id,图片
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.webView)
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        context = MovieDetailActivity.this;
//        获取列表界面传递过来的电影id以及电影名
        itemId = getIntent().getStringExtra("Id");
        title = getIntent().getStringExtra("Title");
        imageUrl = getIntent().getStringExtra("ImageUrl");
        toolbarLayout.setTitle(title);
        Glide.with(context).load(imageUrl).fitCenter().into(imageview);

        Log.d("itemId", itemId);
        String url = UrlHelper.item_url.replace("{id}", itemId);
        getMovieItemInfo(url);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent intent = new Intent(context, WriteCriticsActivity.class);
        intent.putExtra("Title", title);
        intent.putExtra("Flag", -999); //跳转标志
        startActivity(intent);
    }

    public void getMovieItemInfo(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(MovieDetailActivity.this);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        MovieItem item = JSON.parseObject(s, MovieItem.class);

//                        String mobile_url = item.getMobile_url();
//                        Log.d("mobile_url", mobile_url);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
//                        webView.loadUrl(mobile_url);
//                        设置在本WebView中打开页面，而不是在默认浏览器中
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return true;
                            }
                        });
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
}
