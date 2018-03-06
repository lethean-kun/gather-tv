package com.zz;

import com.zz.mapper.TwitterMapper;
import com.zz.mapper.UserMapper;
import com.zz.model.Twitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatherTvApplicationTests {

    @Resource
    TwitterMapper twitterMapper;

    @Test
    public void contextLoads() {
        List<Twitter> twitters = twitterMapper.allTwitter();
        for (Twitter t : twitters) {
            System.out.println(t);
        }
    }

}
