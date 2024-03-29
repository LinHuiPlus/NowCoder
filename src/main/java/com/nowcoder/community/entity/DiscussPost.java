package com.nowcoder.community.entity;/*
 *  @author 张林辉
 *  @version 1.0
 */

import java.util.Date;

public class DiscussPost {

    private int id;
    private int userId;
    private String title;
    private String content;
    private int type; //0 普通 1 置顶
    private int status; //0 正常  1 精华  2 拉黑
    private Date createTime;
    private int commentCount;  //帖子评论的数量
    private double score;

    @Override
    public String toString() {
        return "DiscussPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }

    public DiscussPost(){};

    public DiscussPost(int userId, String title, String content, int type, int status, Date createTime, int commentCount, int score) {
        this.setUserId(userId);
        this.setTitle(title);
        this.setContent(content);
        this.setType(type);
        this.setStatus(status);
        this.setCreateTime(createTime);
        this.setCommentCount(commentCount);
        this.setScore(score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
