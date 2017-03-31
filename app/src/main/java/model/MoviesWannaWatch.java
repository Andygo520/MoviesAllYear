package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by andy on 2017/3/29.
 */
@Entity
public class MoviesWannaWatch {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String date;
    private boolean watched;
    @Keep
    public MoviesWannaWatch(Long id, String name, String date, boolean watched) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.watched = watched;
    }
    @Keep
    public MoviesWannaWatch() {
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
