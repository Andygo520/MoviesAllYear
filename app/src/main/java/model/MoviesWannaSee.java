package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by andy on 2017/3/29.
 */
@Entity
public class MoviesWannaSee {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String date;
    private boolean watched;
    @Generated(hash = 451842529)
    public MoviesWannaSee(Long id, String name, String date, boolean watched) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.watched = watched;
    }
    @Generated(hash = 2085446677)
    public MoviesWannaSee() {
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
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public boolean getWatched() {
        return this.watched;
    }
    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
