package com.nowcoder.community.dao;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 根据实体id查找评论
    List<Comment> findCommentsByEntityId(int entityType, int entityId, int offset, int limit);
    // 返回评论数
    public int findCommentCount(int entityType, int entityId);
    // 添加评论
    public int addComment(Comment comment);
    Comment selectCommentById(int id);
}
