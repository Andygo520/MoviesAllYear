package model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class Movie {
    /**
     * max : 10
     * average : 7.5
     * numRaters : 902
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"average":"7.5","numRaters":902,"min":0}
     * author : [{"name":"比尔·康顿 Bill Condon"}]
     * alt_title :
     * image : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2417948644.jpg
     * title : Beauty and the Beast
     * summary : 《美女与野兽》根据迪士尼1991年经典动画片及闻名全球的经典童话改编，讲述了少女贝儿的奇幻旅程——为了解救触怒野兽的父亲，勇敢善良的她只身一人来到古堡，代替父亲被囚禁其中。贝儿克服了恐惧，和城堡里的魔法家具们成为了朋友，也渐渐发现野兽其实是受了诅咒的王子，他可怖的外表下藏着一颗善良温柔的内心；这个故事也带领观众明白——美不仅仅是外表，更重要的是内心。
     * attrs : {"language":["英语"],"pubdate":["2017-03-17(中国大陆/美国)"],"title":["Beauty and the Beast"],"country":["美国"],"writer":["斯蒂芬·切波斯基 Stephen Chbosky","埃文·斯彼里奥托普洛斯 Evan Spiliotopoulos","琳达·伍尔芙顿 Linda Woolverton","Jeanne-Marie Leprince de Beaumont"],"director":["比尔·康顿 Bill Condon"],"cast":["艾玛·沃森 Emma Watson","丹·史蒂文斯 Dan Stevens","卢克·伊万斯 Luke Evans","凯文·克莱恩 Kevin Kline","乔什·盖德 Josh Gad","伊万·麦克格雷格 Ewan McGregor","伊恩·麦克莱恩 Ian McKellen","艾玛·汤普森 Emma Thompson","斯坦利·图齐 Stanley Tucci","古古·姆巴塔-劳 Gugu Mbatha-Raw","奥德拉·麦当娜 Audra McDonald","内森·麦克 Nathan Mack","哈蒂·莫拉汉 Hattie Morahan","迈克尔·吉普森 Michael Jibson","水野索诺娅 Sonoya Mizuno","亨利·加勒特 Henry Garrett","戴尔·布兰斯顿 Dale Branston"],"movie_duration":["130分钟"],"year":["2017"],"movie_type":["爱情","奇幻","歌舞"]}
     * id : https://api.douban.com/movie/25900945
     * mobile_link : https://m.douban.com/movie/subject/25900945/
     * alt : https://movie.douban.com/movie/25900945
     * tags : [{"count":2273,"name":"迪士尼"},{"count":1913,"name":"童话"},{"count":1527,"name":"爱情"},{"count":1120,"name":"美国"},{"count":1039,"name":"奇幻"},{"count":906,"name":"2017"},{"count":563,"name":"魔幻"},{"count":216,"name":"经典"}]
     */

    private String alt_title;
    private String image;
    private String title;
    private String summary;
    private AttrsBean attrs;
    private String id;
    private String mobile_link;
    private String alt;
    /**
     * name : 比尔·康顿 Bill Condon
     */

    private List<AuthorBean> author;
    /**
     * count : 2273
     * name : 迪士尼
     */

    private List<TagsBean> tags;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public AttrsBean getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrsBean attrs) {
        this.attrs = attrs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class RatingBean {
        private int max;
        private String average;
        private int numRaters;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AttrsBean {
        private List<String> language;
        private List<String> pubdate;
        private List<String> title;
        private List<String> country;
        private List<String> writer;
        private List<String> director;
        private List<String> cast;
        private List<String> movie_duration;
        private List<String> year;
        private List<String> movie_type;

        public List<String> getLanguage() {
            return language;
        }

        public void setLanguage(List<String> language) {
            this.language = language;
        }

        public List<String> getPubdate() {
            return pubdate;
        }

        public void setPubdate(List<String> pubdate) {
            this.pubdate = pubdate;
        }

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public List<String> getCountry() {
            return country;
        }

        public void setCountry(List<String> country) {
            this.country = country;
        }

        public List<String> getWriter() {
            return writer;
        }

        public void setWriter(List<String> writer) {
            this.writer = writer;
        }

        public List<String> getDirector() {
            return director;
        }

        public void setDirector(List<String> director) {
            this.director = director;
        }

        public List<String> getCast() {
            return cast;
        }

        public void setCast(List<String> cast) {
            this.cast = cast;
        }

        public List<String> getMovie_duration() {
            return movie_duration;
        }

        public void setMovie_duration(List<String> movie_duration) {
            this.movie_duration = movie_duration;
        }

        public List<String> getYear() {
            return year;
        }

        public void setYear(List<String> year) {
            this.year = year;
        }

        public List<String> getMovie_type() {
            return movie_type;
        }

        public void setMovie_type(List<String> movie_type) {
            this.movie_type = movie_type;
        }
    }

    public static class AuthorBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        private int count;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
