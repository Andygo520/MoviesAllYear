package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Administrator on 2017/1/4.
 */

@Entity
public class MovieCritics {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String critics;

    @NotNull
    private String createTime;

    @NotNull
    private int stars;// 电影的星级评分

    @Generated(hash = 1592539121)
    public MovieCritics(Long id, @NotNull String name, @NotNull String critics,
            @NotNull String createTime, int stars) {
        this.id = id;
        this.name = name;
        this.critics = critics;
        this.createTime = createTime;
        this.stars = stars;
    }

    @Generated(hash = 951596037)
    public MovieCritics() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCritics() {
        return this.critics;
    }

    public void setCritics(String critics) {
        this.critics = critics;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

}
