package com.zz.service;

import com.zz.model.LiveType;

import java.util.List;

/**
 * Created by lethean on 2017/12/24.
 */
public interface LiveTypeService {

    /**
     * 查所有直播类型
     * @param
     * @return
     */
    List<LiveType> getAllType();

}
