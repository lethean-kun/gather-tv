package com.zz.service.impl;

import com.zz.mapper.TypeMapper;
import com.zz.model.LiveType;
import com.zz.service.LiveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lethean on 2017/12/24.
 */
@Service
public class LiveTypeServiceImpl implements LiveTypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<LiveType> getAllType() {

        List<LiveType> liveTypes = typeMapper.getLiveTypes();
        return liveTypes;
    }
}
