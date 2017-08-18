package com.example.administrator.moviesallyear.retrofit;

import com.example.administrator.moviesallyear.mainactivity.Douban250;

import java.util.ArrayList;

import model.Movie;
import model.MoviesInTheater;
import model.Top250Movie;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/16.
 */

public interface DoubanApiService {

    /**
     * 获取豆瓣电影top250
     *
     */
    @GET("v2/movie/top250")
    Observable<BaseModel<ArrayList<Douban250>>> getMovieTop250();

    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("v2/movie/in_theaters")
    Observable<BaseModel<ArrayList<MoviesInTheater>>> getHotMovie();

    /**
     * 即将上映的电影
     */
    @GET("v2/movie/coming_soon")
    Observable<BaseModel<ArrayList<MoviesInTheater>>> getComingSoonMovie();


    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("v2/movie/subject/{id}")
    Observable<Movie> getMovieDetail(@Path("id") String id);

    /**
     * 获取查询电影
     *
     * @param query 搜索内容
     */
    @GET("v2/movie/search")
    Observable<Top250Movie> getSearchedMovie(@Query("q") String query);

}
