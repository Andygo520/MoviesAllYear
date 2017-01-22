package com.example.administrator.moviesallyear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AppExit;
import com.MyApplication;
import com.example.administrator.moviesallyear.R;
import com.greendao.gen.MovieCriticsDao;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import model.MovieCritics;

public class CriticsDetailActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SearchView mSearchView;
    private Long id;// 条目的主键
    private MovieCriticsDao criticsDao;// 数据库对象
    private MovieCritics critics;//影评对象

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_Date)
    TextView tvDate;
    @BindView(R.id.ratingBar)
    SimpleRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics_detail);
        ButterKnife.bind(this);
        AppExit.getInstance().addActivity(this);
//        初始化ShareSDK操作
        ShareSDK.initSDK(CriticsDetailActivity.this);

        tvTitle.setText("详情");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_delete:
                        criticsDao.deleteByKey(id);
                        Toast.makeText(CriticsDetailActivity.this, "成功删除该条目！", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(CriticsDetailActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.item_share:
//                       显示分享界面
                        showShare();
                        break;
                }
                return false;

            }
        });
//       初始化数据库对象,并通过主键得到具体影评对象
        criticsDao = MyApplication.getInstances().getDaoSession().getMovieCriticsDao();
        id = getIntent().getLongExtra("id", -1);
        critics = criticsDao.load(id);
        if (critics != null) {
            tvName.setText(critics.getName());
            tvContent.setText(critics.getCritics());
            tvDate.setText(critics.getCreateTime());
            ratingBar.setRating(critics.getStars());
            ratingBar.setIndicator(true); //详情页不能点击评分条
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_critics_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(CriticsDetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_back, R.id.iv_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Intent intent1 = new Intent(CriticsDetailActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_edit:
                Intent intent = new Intent(CriticsDetailActivity.this, WriteCriticsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("Flag", 999);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
         oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        String shareText=critics.getCritics()+"——《"+critics.getName()+"》";//设定分享的文本
        oks.setText(shareText);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/1.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(shareText);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }
}
