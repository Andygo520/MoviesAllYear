package com.example.administrator.moviesallyear.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.moviesallyear.R;
import com.example.administrator.moviesallyear.activity.base.ToolbarActivity;
import com.example.administrator.moviesallyear.utils.RxBeauty;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class BeautyActivity extends ToolbarActivity {
    public static final String TRANSIT_PIC = "picture";
    private String url;
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
         url = getIntent().getStringExtra("url");
        Glide.with(BeautyActivity.this).load(url).fitCenter().into(ivBeauty);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beauty_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_download)
            saveImageToGallery();
        return super.onOptionsItemSelected(item);
    }

    private void saveImageToGallery() {
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String title=sdf.format(date);
        Subscription s = RxBeauty.saveImageAndGetPathObservable(this, url, title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Uri>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(BeautyActivity.this,e.toString()+ "\n再试试...",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Uri uri) {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "Beauty");
                        String msg = String.format(getString(R.string.picture_has_save_to),
                                appDir.getAbsolutePath());
                        Toast.makeText(BeautyActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });
        addSubscription(s);
    }
}
