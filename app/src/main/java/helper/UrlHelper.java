package helper;

/**
 * Created by Administrator on 2016/12/27.
 */

public class UrlHelper {
//    https://api.douban.com/v2/movie/in_theaters
    public static String base_url = "https://api.douban.com";
    public static String query_url = base_url + "/v2/movie/search?q={query}"; //搜索对应的URL
    public static String item_url = base_url + "/v2/movie/subject/{id}"; //条目对应的URL
    public static String top250_url = base_url + "/v2/movie/top250?start={start}&count=10"; //top250的URL
    public static String in_theaters_url = base_url + "/v2/movie/in_theaters"; //正上映的电影URL
    public static String beauty_url="http://gank.io/api/data/iOS/1/1";//美女url
}
