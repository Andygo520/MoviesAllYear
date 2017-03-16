package com.example.administrator.moviesallyear.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.moviesallyear.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helper.UrlHelper;
import model.MovieItem;
import model.Result;
import model.Top250Adapter;
import model.Top250Movie;

public class Top250Activity extends AppCompatActivity {

    private List<MovieItem> data = new ArrayList<>();
    private List<MovieItem> movieList = new ArrayList<>();
    private int start = 0;//从第start条开始显示
    @BindView(R.id.picker)
    DiscreteScrollView picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
        ButterKnife.bind(this);

        String url = UrlHelper.top250_url.replace("{start}", start + "");
        getTop250(url);

        picker.setScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScrollEnd(RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                Log.d("adapterPosition", adapterPosition + "");
                if (adapterPosition + 1 == start + 10) {
                    start += 10;
                    String url1 = UrlHelper.top250_url.replace("{start}", start + "");
                    getTop250(url1);
                }
            }

            @Override
            public void onScroll(float scrollPosition) {

            }
        });

    }

    private void getTop250(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(Top250Activity.this);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Result result = JSON.parseObject(s, Result.class);
                        List<Top250Movie> list = JSON.parseArray(result.getSubjects().toString(), Top250Movie.class);

                        for (Top250Movie top250Movie : list) {
                            String title = top250Movie.getTitle();
                            String imageUrl = top250Movie.getImages().getLarge();
                            double rating = top250Movie.getRating().getAverage();
                            data.add(new MovieItem(title,rating, imageUrl));
                        }
                        if (result.getStart() > 0) {
                            movieList.addAll(data);
                            picker.setAdapter(new Top250Adapter(Top250Activity.this, movieList, R.layout.item_250));
                        } else
                            picker.setAdapter(new Top250Adapter(Top250Activity.this, data, R.layout.item_250));
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
