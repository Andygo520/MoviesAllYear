/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.administrator.moviesallyear.retrofit;

/**
 * Created by drakeet on 8/9/15.
 */
public class QuanysFactory {

    protected static final Object monitor = new Object();
    static DoubanApi doubanSingleton = null;
    static GankApi   gankSingleton = null;

    public static final int meizhiSize = 10;
    public static final int gankSize = 5;

    public static boolean isDebug = true;


    public static DoubanApi getDoubanSingleton() {
        synchronized (monitor) {
            if (doubanSingleton == null) {
                doubanSingleton = new QuanysRetrofit().getdoubanService();
            }
            return doubanSingleton;
        }
    }

    public static GankApi getGankSingleton() {
        synchronized (monitor) {
            if (gankSingleton == null) {
                gankSingleton = new QuanysRetrofit().getGankService();
            }
            return gankSingleton;
        }
    }

}
