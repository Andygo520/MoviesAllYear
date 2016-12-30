package helper;

/**
 * Created by Administrator on 2016/12/27.
 */

public class UrlHelper {
    public static String base_url = "https://api.douban.com";
    public static String query_url = base_url + "/v2/movie/search?q={query}"; //搜索对应的URL
    public static String item_url = base_url + "/v2/movie/subject/{id}"; //条目对应的URL
}
