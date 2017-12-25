package com.zz.service;


/**
 * @author dzk
 * Created by lethean on 2017/12/22.
 */
public interface SpiderService {
    /**
     *抓取直播列表
     * @param
     * @return
     */
    int forInsertLive() throws Exception;

    /**
     *抓取直播类型
     * @param
     * @return
     */
    int forInsertType() throws Exception;
}
