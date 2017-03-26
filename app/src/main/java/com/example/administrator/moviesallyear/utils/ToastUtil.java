package com.example.administrator.moviesallyear.utils;

import android.widget.Toast;

import com.MoviesAllYearApplication;


/**
 * Created by jingbin on 2016/12/14.
 * 单例Toast
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(MoviesAllYearApplication.getInstances(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
}
