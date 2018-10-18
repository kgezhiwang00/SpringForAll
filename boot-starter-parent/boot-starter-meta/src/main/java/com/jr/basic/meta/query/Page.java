//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query;

import java.io.Serializable;
import org.springframework.util.Assert;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PAGE_ARGUMENT = "_page_";
    public static final String PAGE_SIZE_ARGUMENT = "_max_";
    public static final String DATA_TOTAL_ARGUMENT = "_total_";
    public static final String DATA_ARGUMENT = "datas";
    public static final String ORDER_ARGUMENT = "_orderBy_";
    public static final String PAGE_QUERY_STRING = "page.";
    public static final int DEFAULT_PAGE_SIZE = 15;
    private Long total;
    private Integer max = Integer.valueOf(15);
    private Integer curPage = Integer.valueOf(1);
    private Integer pageCount = Integer.valueOf(0);
    private boolean pageable = false;
    private Iterable<T> datas;

    public Page() {
    }

    public void refresh() {
        Assert.notNull(this.total, "total not be null");
        Assert.notNull(this.max, "page max not be null");
        this.pageCount = (int)(this.total.longValue() / (long)this.max.intValue());
        if (this.total.longValue() % (long)this.max.intValue() != 0L) {
            Integer var1 = this.pageCount;
            Integer var2 = this.pageCount = this.pageCount.intValue() + 1;
        }

        this.curPage = Math.min(this.curPage.intValue(), this.pageCount.intValue());
        this.curPage = Math.max(this.curPage.intValue(), 1);
    }

    public Long getTotal() {
        return this.total;
    }

    public Page<T> setTotal(Long total) {
        this.total = total;
        this.refresh();
        return this;
    }

    public Integer getMax() {
        return this.max;
    }

    public int getOffset() {
        return (this.curPage.intValue() - 1) * this.max.intValue();
    }

    public Integer getCurPage() {
        return this.curPage;
    }

    public Page<T> setMax(Integer max) {
        this.max = max;
        return this;
    }

    public Page<T> setCurPage(Integer curPage) {
        this.curPage = curPage;
        return this;
    }

    public int getPageCount() {
        return this.pageCount.intValue();
    }

    public Page<T> setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Iterable<T> getDatas() {
        return this.datas;
    }

    public Page<T> setDatas(Iterable<T> datas) {
        this.datas = datas;
        return this;
    }

    public boolean isPageable() {
        return this.pageable;
    }

    public Page<T> setPageable(boolean pageable) {
        this.pageable = pageable;
        return this;
    }
}
