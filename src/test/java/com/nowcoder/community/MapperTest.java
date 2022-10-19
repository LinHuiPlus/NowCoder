package com.nowcoder.community;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
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

    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void testSensitive() {
        //String test = "这里可以赌博嫖娼啊，也可以嫖娼，同事也是个傻逼。";
        //System.out.println(sensitiveFilter.filter(test));
    }
    
}
