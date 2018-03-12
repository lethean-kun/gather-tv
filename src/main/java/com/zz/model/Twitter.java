package com.zz.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author dzk
 * Created by lethean on 2018/3/5.
 */
public class Twitter {
    private int id;
    private int userId;
    private String feeling;
    private Date creatData;
    private Date deleteDate;
    private int likeHit;
    private int dislikeHit;
    private int replyHit;
    private User user;
    private List<Comment> comments;
    private List<String> imagesList;


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

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public Date getCreatData() {
        return creatData;
    }

    public void setCreatData(Date creatData) {
        this.creatData = creatData;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public int getLikeHit() {
        return likeHit;
    }

    public void setLikeHit(int likeHit) {
        this.likeHit = likeHit;
    }

    public int getDislikeHit() {
        return dislikeHit;
    }

    public void setDislikeHit(int dislikeHit) {
        this.dislikeHit = dislikeHit;
    }

    public int getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(int replyHit) {
        this.replyHit = replyHit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public String toString() {
        return "Twitter{" +
                "id=" + id +
                ", userId=" + userId +
                ", feeling='" + feeling + '\'' +
                ", creatData=" + creatData +
                ", deleteDate=" + deleteDate +
                ", likeHit=" + likeHit +
                ", dislikeHit=" + dislikeHit +
                ", replyHit=" + replyHit +
                ", user=" + user.getUsername() +
                '}';
    }
}
