package com.zz.model;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
public class Result {
    //状态
    private int status;
    //信息
    private String message;
    //数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
