package com.nowcoder.community.util;/*
 *  @author 张林辉
 *  @version 1.0
 */

public interface CommunityConstant {
    /**
     * 私信消息每页显示数量
     */
    int PAGE_LIMIT_COUNT = 5;
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认超时时长
     */
    int DEFAULT_EXPIRED_SECONDS = 1000 * 6;

    /**
     *
     */
    int REMEMBER_EXPIRED_SECONDS = 1000 * 6 * 21;

    /**
     * 实体类型：帖子
     */
    int ENTITY_TYPE_DISCUSS = 1;

    /**
     * 实体类型：评论
     */
    int ENTITY_TYPE_COMMENT = 2;

    /**
     * 实体类型: 用户
     */
    int ENTITY_TYPE_USER = 3;

    /**
     * 评论每页显示数量
     */
    int COMMENT_LIMIT = 10;
    /**
     * 私信消息的状态
     */
    int MESSAGE_STATUS_READ = 1;   // 已读
    int MESSAGE_STATUS_UNREAD = 0; // 未读
}
