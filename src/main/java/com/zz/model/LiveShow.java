package com.zz.model;

import java.io.Serializable;

/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
public class LiveShow implements Comparable<LiveShow>,Serializable {
    private int id;
    private String personName;
    private String picUrl;
    private String type;
    private String liveUrl;
    private String liveTitle;
    private String showNum;
    private String msgChannel;
    private int isShow;
    /**
     *  不同类别直播平台标识(1-虎牙龙珠、2-全民战旗)
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
    }

    public String getShowNum() {
        return showNum;
    }

    public void setShowNum(String showNum) {
        this.showNum = showNum;
    }

    public String getMsgChannel() {
        return msgChannel;
    }

    public void setMsgChannel(String msgChannel) {
        this.msgChannel = msgChannel;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LiveShow{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", type='" + type + '\'' +
                ", liveUrl='" + liveUrl + '\'' +
                ", liveTitle='" + liveTitle + '\'' +
                ", showNum='" + showNum + '\'' +
                ", msgChannel='" + msgChannel + '\'' +
                '}';
    }

    //重写排序方法 按直播人数降序
    @Override
    public int compareTo(LiveShow o) {
        if(Double.valueOf(this.getShowNum())<Double.valueOf(o.getShowNum())){
            return 1;
        }
        return -1;
    }
}
