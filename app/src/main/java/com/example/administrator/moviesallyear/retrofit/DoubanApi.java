package com.example.administrator.moviesallyear.retrofit;

import model.Movie;
import model.Top250Movie;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface DoubanApi {
    /**
     * 获取豆瓣电影top250
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     */
    @GET("v2/movie/top250")
    Observable<Top250Movie> getMovieTop250(@Query("start") int start, @Query("count") int count);


    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("v2/movie/in_theaters")
    Observable<Top250Movie> getHotMovie();

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
