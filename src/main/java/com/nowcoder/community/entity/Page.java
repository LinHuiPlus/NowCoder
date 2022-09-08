package com.nowcoder.community.entity;/*
 *  @author 张林辉
 *  @version 1.0
 */

public class Page {
    // 当前页数
    private int currentPage=1;
    // 每页大小
    private int pageLimit=10;
    // 数据总数
    private int rows;
    // 页面链接路径
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage > 0 ? currentPage : 1;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit > 0 && pageLimit <= 100 ? pageLimit : 10;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows >= 0 ? rows : 0;
    }

    /**
     * 获取当前页的起始行
     */
    public int getOffset() {
        return (currentPage-1) * pageLimit;
    }
    /**
     * 获取总页数
     */
    public int getTotalPage() {
        int totalPage = rows / pageLimit;
        return rows % pageLimit == 0 ? totalPage : (totalPage + 1);
    }
    /**
     * 获取起始页
     */
    public int getFrom() {
        int fromPage = currentPage-2;
        return fromPage >= 1 ? fromPage : 1;
    }
    /**
     * 获取结尾页
     */
    public int getTo() {
        int toPage = currentPage + 2;
        int totalPage = getTotalPage();
        return toPage > totalPage ? totalPage : toPage;
    }
}
