package com.example.administrator.moviesallyear.retrofit;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by Administrator on 2017/4/25.
 */

public class CacheProvider {
    Context mContext;

    public CacheProvider(Context context) {
        mContext = context;
    }

    public Cache provideCache() {//使用应用缓存文件路径，缓存大小为10MB
        return new Cache(mContext.getCacheDir(), 10240 * 1024);
    }
}
