package com.zz.model;

import java.util.Date;

/**
 * @author dzk
 * Created by lethean on 2018/3/6.
 */
public class HitRecord {
    private int id;
    private int userId;
    private int twitterId;
    private Date hitDate;
    /**
     * 0-大家当无事发生
     * 2-点了赞
     * 1-踩了一脚
     */
    private int isLike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(int twitterId) {
        this.twitterId = twitterId;
    }

    public Date getHitDate() {
        return hitDate;
    }

    public void setHitDate(Date hitDate) {
        this.hitDate = hitDate;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }
}
