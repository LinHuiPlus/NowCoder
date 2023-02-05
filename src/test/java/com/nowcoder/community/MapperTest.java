package com.nowcoder.community;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void testInsertTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date(System.currentTimeMillis() +1000 * 60 * 2));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectTicket() {
        System.out.println(loginTicketMapper.selectByTicket("abc"));
    }

    @Test
    public void testUpdateTicket() {
        loginTicketMapper.updateStatus("abc", 0);
    }

    @Test
    public void testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhanglinhui");
        System.out.println(CommunityUtil.getJsonString(123, "nihao", map));
    }

    @Test
    public void testSensitive() {
        String test = "这里可以赌博嫖娼啊，也可以嫖娼，同事也是个傻逼。";
        System.out.println(sensitiveFilter.filter(test));
        test = "这里可以赌*博@嫖#娼啊，也可以嫖¥¥娼，同事也是个@傻#¥逼。";
        System.out.println(sensitiveFilter.filter(test));
        test = "你是个&二&&百%%五啊。";
        System.out.println(sensitiveFilter.filter(test));
    }

    @Test
    public void testInsert() {
        DiscussPost post = new DiscussPost();
        post.setTitle("你是个&二&&百%%五啊。");
        post.setContent("这里可以赌*博@嫖#娼啊，也可以嫖¥¥娼，");
        post.setType(1);
        post.setStatus(1);
        post.setCreateTime(new Date());
        post.setCommentCount(1);
        post.setScore(1);
        discussPostMapper.insertDiscussPost(post);
    }

    @Test
    public void testFindPost() {
        System.out.println(discussPostMapper.selectDiscussPostById(283));
    }

    @Autowired
    public CommentService commentService;

    @Test
    public void testComment() {
        List<Comment> list = commentService.getCommentsByEntityId(1, 228, 0, 5);
        list.forEach(System.out::println);
    }
}
