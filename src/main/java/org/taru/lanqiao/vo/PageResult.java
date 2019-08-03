package org.taru.lanqiao.vo;

import java.util.List;

/**
 * 分页结果存储
 */
public class PageResult {
    private int pageNum;	    // 当前页数
    private int pageSize;	    // 分页大小
    private int total;		    // 总数据
    private int pages;		    // 总分页
    private List<Object> list;	// 数据

    // get set方法
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public List<Object> getList() {
        return list;
    }
    public void setList(List<Object> list) {
        this.list = list;
    }
}
