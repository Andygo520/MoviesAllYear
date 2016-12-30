package com.example.administrator.moviesallyear;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.UrlHelper;
import model.MovieItem;

public class ScrollingActivity extends AppCompatActivity {

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
    @BindView(R.id.et_critic)
    EditText etCritic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        获取列表界面传递过来的条目id
        String itemId = getIntent().getStringExtra("Id");
        Log.d("itemId", itemId);

        String url = UrlHelper.item_url.replace("{id}", itemId);

        getMovieItemInfo(url);
    }

    @OnClick(R.id.fab)
    public void onClick() {
       webView.setVisibility(View.GONE);
        etCritic.setVisibility(View.VISIBLE);
        etCritic.setHint("赶快写点影评吧···");
        etCritic.setHintTextColor(Color.parseColor(String.valueOf(R.color.color_light_grey)));
    }

    public void getMovieItemInfo(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(ScrollingActivity.this);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        MovieItem item = JSON.parseObject(s, MovieItem.class);

                        String mobile_url = item.getMobile_url();
                        webView.loadUrl(mobile_url);
                        webView.setWebViewClient(new WebViewClient(){
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
