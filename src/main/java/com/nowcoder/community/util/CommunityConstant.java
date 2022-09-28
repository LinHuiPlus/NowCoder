package com.nowcoder.community.util;/*
 *  @author 张林辉
 *  @version 1.0
 */

public interface CommunityConstant {
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
}
