package model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MovieItem {

    private String title;//电影名
    private List<String> casts;//演员
    private String imageUrl;//图片地址

    public MovieItem(String title, List<String> casts, String imageUrl) {
        this.title = title;
        this.casts = casts;
        this.imageUrl = imageUrl;
    }
    public MovieItem(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCasts() {
        return casts;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCasts(List<String> casts) {
        this.casts = casts;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
