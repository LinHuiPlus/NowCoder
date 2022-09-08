package com.nowcoder.community;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);
        User user1 = userMapper.selectByName("liubei");
        System.out.println(user1);
    }

    @Test
    public void testSelectDiscussPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0,0,10);
        discussPosts.forEach(System.out::println);
        System.out.println(discussPostMapper.selectDiscussPostRows(0));
    }

    @Test
    public void test(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            System.out.println(s1[i]);
        }
    }

}
