package com.example.administrator.moviesallyear.mainactivity;

import java.util.List;


/**
 * Created by Administrator on 2017/8/18.
 */

public class Douban250 {
    /**
     * max : 10
     * average : 7.8
     * stars : 40
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"average":7.8,"stars":"40","min":0}
     * genres : ["剧情","喜剧","冒险"]
     * title : 一条狗的使命
     * casts : [{"alt":"https://movie.douban.com/celebrity/1000088/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1433487849.12.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1433487849.12.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1433487849.12.jpg"},"name":"布丽特·罗伯森","id":"1000088"},{"alt":"https://movie.douban.com/celebrity/1002679/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/52.jpg","large":"https://img3.doubanio.com/img/celebrity/large/52.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/52.jpg"},"name":"丹尼斯·奎德","id":"1002679"},{"alt":"https://movie.douban.com/celebrity/1049592/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1376584681.68.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1376584681.68.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1376584681.68.jpg"},"name":"佩吉·利普顿","id":"1049592"}]
     * collect_count : 60949
     * original_title : A Dog's Purpose
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1018014/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/4333.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4333.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4333.jpg"},"name":"拉斯·霍尔斯道姆","id":"1018014"}]
     * year : 2017
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2432493858.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2432493858.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2432493858.jpg"}
     * alt : https://movie.douban.com/subject/6873143/
     * id : 6873143
     */

    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    /**
     * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2432493858.jpg
     * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2432493858.jpg
     * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2432493858.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    /**
     * alt : https://movie.douban.com/celebrity/1000088/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1433487849.12.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1433487849.12.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1433487849.12.jpg"}
     * name : 布丽特·罗伯森
     * id : 1000088
     */

    private List<CastsBean> casts;
    /**
     * alt : https://movie.douban.com/celebrity/1018014/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/4333.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4333.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4333.jpg"}
     * name : 拉斯·霍尔斯道姆
     * id : 1018014
     */

    private List<DirectorsBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean {
        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        private String alt;
        /**
         * small : https://img3.doubanio.com/img/celebrity/small/1433487849.12.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/1433487849.12.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/1433487849.12.jpg
         */

        private CastsBean.AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public CastsBean.AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(CastsBean.AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        private String alt;
        /**
         * small : https://img3.doubanio.com/img/celebrity/small/4333.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/4333.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/4333.jpg
         */

        private DirectorsBean.AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public DirectorsBean.AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(DirectorsBean.AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
