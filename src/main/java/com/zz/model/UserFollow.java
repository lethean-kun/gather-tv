package com.zz.model;

import java.io.Serializable;

/**
 * @author dzk
 * Created by lethean on 2018/1/28.
 */
public class UserFollow implements Serializable{

    private int id;
    private int userId;
    private int roomId;
    private int status;

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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
