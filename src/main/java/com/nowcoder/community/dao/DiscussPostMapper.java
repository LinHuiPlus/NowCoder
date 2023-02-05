package com.nowcoder.community.dao;/*
 *  @author 张林辉
 *  @version 1.0
 */

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@Param注解用于取别名 需要在SQL中用到userId这个参数时添加
    //如果只有一个参数，并且在<if>中使用，则必须取别名
    int selectDiscussPostRows(@Param("userId") int userId);   //userId为0代表查询首页

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(@Param("Id") int Id);

    public int updateCommentCount(int id, int commentCount);
}
