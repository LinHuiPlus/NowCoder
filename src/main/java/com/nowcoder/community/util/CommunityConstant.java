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
    /**
     * 消息主题: 评论
     */
    String TOPIC_COMMENT = "comment";
    /**
     * 消息主题: 点赞
     */
    String TOPIC_LIKE = "like";
    /**
     * 消息主题: 关注
     */
    String TOPIC_FOLLOW = "follow";
    /**
     * 主题: 发帖
     */
    String TOPIC_PUBLISH = "publish";

    /**
     * 主题: 删帖
     */
    String TOPIC_DELETE = "delete";

    /**
     * 主题: 分享
     */
    String TOPIC_SHARE = "share";
    /**
     * 系统用户ID
     */
    int SYSTEM_USER_ID = 1;
    /**
     * 权限: 普通用户
     */
    String AUTHORITY_USER = "user";

    /**
     * 权限: 管理员
     */
    String AUTHORITY_ADMIN = "admin";

    /**
     * 权限: 版主
     */
    String AUTHORITY_MODERATOR = "moderator";
}
