package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

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

    @Generated(hash = 2092180991)
    public MovieCritics(Long id, @NotNull String name, @NotNull String critics,
            @NotNull String createTime) {
        this.id = id;
        this.name = name;
        this.critics = critics;
        this.createTime = createTime;
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

}
