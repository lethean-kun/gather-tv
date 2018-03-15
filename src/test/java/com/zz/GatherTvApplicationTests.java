package com.zz;

import com.zz.mapper.TwitterMapper;
import com.zz.service.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatherTvApplicationTests {

    @Resource
    TwitterMapper twitterMapper;

    @Resource
    SpiderService spiderService;

    @Test
    public void contextLoads() throws Exception {
        spiderService.forInsertLive();
    }

}
