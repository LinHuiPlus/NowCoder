package com.nowcoder.community.controller;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String responseFirst(Model model, Page page, @RequestParam(name = "orderMode", defaultValue = "0")int orderMode) {
        // 方法调用前，SpringMVC会自动实例化model和page，并将page注入到model
        // 所以在thymeleaf中可以直接访问page中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode="+orderMode);

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getPageLimit(), orderMode);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(list != null) {
            for(DiscussPost discussPost : list){
                Map<String, Object> map = new HashMap<>();
                map.put("post", discussPost);
                map.put("user", userService.getUserById(discussPost.getUserId()));

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_DISCUSS, discussPost.getId());

                map.put("likeCount", likeCount);

                discussPosts.add(map);

            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderMode", orderMode);

        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }

}
