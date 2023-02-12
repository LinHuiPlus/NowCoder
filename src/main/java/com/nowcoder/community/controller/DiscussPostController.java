package com.nowcoder.community.controller;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJsonString(403, "未登录！");
        }
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setTitle(title);
        discussPost.setContent(content);
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);
        return CommunityUtil.getJsonString(0,"发帖成功！");
    }

    @RequestMapping(path="/detail/{discussPostId}", method=RequestMethod.GET)
    public String findDiscussDetail(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        User user = userService.getUserById(discussPost.getUserId());
        model.addAttribute("post",discussPost);
        model.addAttribute("user", user);

        page.setPageLimit(COMMENT_LIMIT);
        page.setPath("/discuss/detail/" + discussPostId);
        page.setRows(discussPost.getCommentCount());

        // 当前帖子下的当前页的所有评论
        List<Comment> commentList = commentService.getCommentsByEntityId(ENTITY_TYPE_DISCUSS, discussPost.getId(), page.getOffset(), page.getPageLimit());
        // 存放所有评论对象数据，用于在页面展示
        List<Map<Object, Object>> commentObjectList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                Map<Object, Object> commentMap = new HashMap<>();
                commentMap.put("user", userService.getUserById(comment.getUserId()));
                commentMap.put("comment", comment);
                // 当前评论下的所有回复评论
                List<Comment> comments = commentService.getCommentsByEntityId(ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                List<Map<Object, Object>> replyList = new ArrayList<>();
                if (comments != null) {
                    for (Comment subcomment : comments) {
                        Map<Object, Object> replyMap = new HashMap<>();
                        replyMap.put("user", userService.getUserById(subcomment.getUserId()));
                        replyMap.put("comment", subcomment);
                        // 该回复是不是指定目标回复
                        User target = subcomment.getTargetId() == 0 ? null : userService.getUserById(subcomment.getTargetId());
                        replyMap.put("target", target);

                        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, subcomment.getId());
                        int likeStatus = 0;
                        if (hostHolder.getUser() != null)
                            likeStatus = likeService.findEntityStatusByUserId(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, subcomment.getId());
                        replyMap.put("likeCount", likeCount);
                        replyMap.put("likeStatus", likeStatus);

                        replyList.add(replyMap);
                    }
                }

                commentMap.put("replys", replyList);
                commentMap.put("replyCount", commentService.getCommentsCount(ENTITY_TYPE_COMMENT, comment.getId()));

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, comment.getId());
                int likeStatus = 0;
                if (hostHolder.getUser() != null)
                    likeStatus = likeService.findEntityStatusByUserId(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, comment.getId());
                commentMap.put("likeCount", likeCount);
                commentMap.put("likeStatus", likeStatus);

                commentObjectList.add(commentMap);
            }
        }
        long postCount = likeService.findEntityLikeCount(ENTITY_TYPE_DISCUSS, discussPostId);
        int likeStatus = 0;
        if (hostHolder.getUser() != null)
            likeStatus = likeService.findEntityStatusByUserId(hostHolder.getUser().getId(), ENTITY_TYPE_DISCUSS, discussPostId);

        model.addAttribute("likeCount", postCount);
        model.addAttribute("likeStatus", likeStatus);

        model.addAttribute("comments", commentObjectList);
        return "/site/discuss-detail";
    }

}
