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

import model.Beauty;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

// @formatter:off

/**
 * Created by drakeet on 8/9/15.
 */
public interface GankApi {
//  获取干货集中营最新的福利图
    @GET("data/福利/1/1" )
    Observable<Beauty> getMeizhiData(@Header("Cache-Time") String time);
}
