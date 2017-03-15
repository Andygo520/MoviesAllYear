package model;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Result<T> {

    private int count;
    private int start;
    private int total;
    private T  subjects;//使用泛型代表任意对象

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }
}
