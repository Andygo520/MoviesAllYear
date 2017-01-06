package com.example.administrator.moviesallyear;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.greendao.gen.MovieCriticsDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import helper.OnRecyclerItemClickListener;
import helper.UrlHelper;
import model.CriticsAdapter;
import model.MovieInSearch;
import model.MovieSearchedAdapter;

public class MainActivity extends AppCompatActivity {
    private String query;//用户搜索的内容
    private String url;//用户搜索的信息对应的URL
    private List<String> titles, casts, imageUrls;// 用来存放电影标题、演员、电影图片的列表
    private MovieCriticsDao criticsDao;  // 用来进行数据库操作的dao对象

    @BindView(R.id.llInputQuery)
    LinearLayout llInputQuery;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.etSearchInput)
    EditText etSearchInput;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        获取MovieCriticsDao对象
        criticsDao = MyApplication.getInstances().getDaoSession().getMovieCriticsDao();

        ArrayList<String> items = new ArrayList<>();
        items.add("搜电影");
        items.add("写影评");
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.search_movie);
        images.add(R.drawable.edit_critics);

        titles = new ArrayList<>();
        casts = new ArrayList<>();
        imageUrls = new ArrayList<>();

//        配置BoomMenuButton
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            switch (index) {
                                case 0:
                                    llInputQuery.setVisibility(View.VISIBLE);
                                    break;
                                case 1:
                                    Intent intent = new Intent(MainActivity.this, CriticsActivity.class);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    })
                    .normalText(items.get(i))
                    .highlightedColor(R.color.colorBase)
                    .textPadding(new Rect(10, 10, 10, 10))
                    .normalImageRes(images.get(i));
            bmb.addBuilder(builder);
        }

//  当EditText设置了imeOptions属性后，利用该方法给回车键设置点击事件
        etSearchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    query = etSearchInput.getText().toString().trim();
                    try {
                        query = URLEncoder.encode(query, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    if (query.equals("")) {
                        Toast.makeText(MainActivity.this, "请输入内容后再搜索", Toast.LENGTH_SHORT).show();
                    } else {
                        url = UrlHelper.query_url.replace("{query}", query);
                        Log.d("queryurl", url);
                        getMovieInfor(url);
                    }
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }

        });
//     监听文本框内容变化
        etSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(""))
                    ivDelete.setVisibility(View.GONE);
                else
                    ivDelete.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//   从数据库查询数据(根据创建时间升序排列)
        List movieCriticsList = criticsDao.queryBuilder()
                .orderDesc(MovieCriticsDao.Properties.CreateTime)
                .list();
//        List<MovieCritics> movieCriticsList = criticsDao.loadAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new CriticsAdapter(movieCriticsList, MainActivity.this));
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(MainActivity.this, CriticsActivity.class);
                long id= criticsDao.queryBuilder()
                        .orderDesc(MovieCriticsDao.Properties.CreateTime)
                        .list().get(vh.getLayoutPosition()-1).getId();
                intent.putExtra("Flag",999);
                intent.putExtra("id",id);
                Log.d("idid",id+"");
                startActivity(intent);
            }
        });

    }

    public void getMovieInfor(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        MovieInSearch info = JSON.parseObject(s, MovieInSearch.class);
                        final List<MovieInSearch.SubjectsBean> subjects = info.getSubjects();
//                      每次从后台取数据前先清空列表数据
                        titles.clear();
                        casts.clear();
                        imageUrls.clear();
//                        通过一层循环取出电影条目的标题、大图标地址URL
                        for (MovieInSearch.SubjectsBean item : subjects) {
                            titles.add(item.getTitle());
                            List<MovieInSearch.SubjectsBean.CastsBean> castsBean = item.getCasts();
//                            通过二层循环取出演员姓名列表
                            String strCasts = "";
                            for (MovieInSearch.SubjectsBean.CastsBean actors : castsBean) {
                                strCasts += actors.getName() + "，";
                            }
//                          使用SpannableStringBuilder让文本框同时显示不同的样式
                            SpannableStringBuilder ssb = new SpannableStringBuilder();
                            ssb.append("主演：");
                            if (strCasts.length() > 0)
                                ssb.append(strCasts.substring(0, strCasts.length() - 1));
                            ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.colorBase));
                            ssb.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                            casts.add(ssb.toString());

                            imageUrls.add(item.getImages().getLarge());
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(new MovieSearchedAdapter(titles, casts, imageUrls, MainActivity.this));
                        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
                            @Override
                            public void onLongClick(RecyclerView.ViewHolder vh) {
                            }

                            @Override
                            public void onItemClick(RecyclerView.ViewHolder vh) {
                                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                                intent.putExtra("Id", subjects.get(vh.getLayoutPosition() - 1).getId());
                                startActivity(intent);
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

    @OnClick({R.id.ivDelete, R.id.llSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDelete:
                etSearchInput.setText("");
                break;

            case R.id.llSearch:
//            获取用户的输入，并对输入内容进行utf-8格式的编码（避免中文的不兼容）
                query = etSearchInput.getText().toString().trim();
                if (query.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入内容后再搜索", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    query = URLEncoder.encode(query, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url = UrlHelper.query_url.replace("{query}", query);
                getMovieInfor(url);
                break;
        }
    }

}
