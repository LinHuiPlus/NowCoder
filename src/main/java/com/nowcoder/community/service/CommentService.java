package com.nowcoder.community.service;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.dao.CommentMapper;
import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService implements CommunityConstant {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<Comment> getCommentsByEntityId(int entityType, int entityId, int offset, int limit) {
        return commentMapper.findCommentsByEntityId(entityType, entityId, offset, limit);
    }

    public int getCommentsCount(int entityType, int entityId) {
        return commentMapper.findCommentCount(entityType, entityId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertCommentByEntityId(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        int count = commentMapper.addComment(comment);

        if (comment.getEntityType() == ENTITY_TYPE_DISCUSS) {
            discussPostService.updateCommentCountById(comment.getEntityId(), commentMapper.findCommentCount(comment.getEntityType(), comment.getEntityId()));
        }

        return count;
    }
}
