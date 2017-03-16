package model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MovieItem {

    private String title;//电影名


    private double rating;//评分
    private List<String> casts;//演员
    private String imageUrl;//图片地址

    public MovieItem(String title, List<String> casts, String imageUrl) {
        this.title = title;
        this.casts = casts;
        this.imageUrl = imageUrl;
    }

    public MovieItem(String title, double rating, String imageUrl) {
        this.title = title;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
