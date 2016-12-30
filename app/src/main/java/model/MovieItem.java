package model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */

public class MovieItem {
    /**
     * max : 10
     * average : 8.5
     * stars : 45
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"average":8.5,"stars":"45","min":0}
     * reviews_count : 747
     * wish_count : 67011
     * douban_site :
     * year : 2013
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2250287733.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2250287733.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2250287733.jpg"}
     * alt : https://movie.douban.com/subject/6874403/
     * id : 6874403
     * mobile_url : https://movie.douban.com/subject/6874403/mobile
     * title : 再次出发之纽约遇见你
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/6874403
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["喜剧","爱情","音乐"]
     * collect_count : 154180
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054448/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/10192.jpg","large":"https://img3.doubanio.com/img/celebrity/large/10192.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/10192.jpg"},"name":"凯拉·奈特莉","id":"1054448"},{"alt":"https://movie.douban.com/celebrity/1040505/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/909.jpg","large":"https://img1.doubanio.com/img/celebrity/large/909.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/909.jpg"},"name":"马克·鲁弗洛","id":"1040505"},{"alt":"https://movie.douban.com/celebrity/1017966/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1761.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1761.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1761.jpg"},"name":"詹姆斯·柯登","id":"1017966"},{"alt":"https://movie.douban.com/celebrity/1312964/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/20419.jpg","large":"https://img1.doubanio.com/img/celebrity/large/20419.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/20419.jpg"},"name":"海莉·斯坦菲尔德","id":"1312964"}]
     * current_season : null
     * original_title : Begin Again
     * summary : 格雷塔（凯拉·奈特莉 Keira Knightley 饰）抛弃了一切跟随着男友戴夫（亚当·李维 Adam Levine 饰）来到美国纽约实现他们的音乐和爱情梦想，没想到曾经幻想过的美好一切化作泡影，在获得了更好的工作机会后，戴夫果断抛弃了格雷塔，留下她一人苦苦在底层挣扎。
     丹（马克·鲁法洛 Mark Ruffalo 饰）最近的生活也不好过，他发现自己无法从思念前妻马瑞安（凯瑟琳·基纳 Catherine Keener 饰）的感情中走出来，与此同时，他的女儿维奥莱特（海莉·斯坦菲尔德 Hailee Steinfeld 饰）正处于一生中最叛逆的阶段。丹和格雷塔，命运让这失意的两人于一间小小的酒吧里相遇了，等待着他们的，会是怎样充满波折而又浪漫满满的故事呢？©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1280127/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/48271.jpg","large":"https://img3.doubanio.com/img/celebrity/large/48271.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/48271.jpg"},"name":"约翰·卡尼","id":"1280127"}]
     * comments_count : 53612
     * ratings_count : 134962
     * aka : ["再次出发","歌曲改变人生","一切从音乐再开始(港)","曼哈顿恋习曲(台)","纽约小情歌","为乐而生","重新开始","重来","Can a Song Save Your Life?","New York Melody"]
     */

    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    /**
     * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2250287733.jpg
     * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2250287733.jpg
     * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2250287733.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    /**
     * alt : https://movie.douban.com/celebrity/1054448/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/10192.jpg","large":"https://img3.doubanio.com/img/celebrity/large/10192.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/10192.jpg"}
     * name : 凯拉·奈特莉
     * id : 1054448
     */

    private List<CastsBean> casts;
    /**
     * alt : https://movie.douban.com/celebrity/1280127/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/48271.jpg","large":"https://img3.doubanio.com/img/celebrity/large/48271.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/48271.jpg"}
     * name : 约翰·卡尼
     * id : 1280127
     */

    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
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
         * small : https://img3.doubanio.com/img/celebrity/small/10192.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/10192.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/10192.jpg
         */

        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
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
         * small : https://img3.doubanio.com/img/celebrity/small/48271.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/48271.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/48271.jpg
         */

        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
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
