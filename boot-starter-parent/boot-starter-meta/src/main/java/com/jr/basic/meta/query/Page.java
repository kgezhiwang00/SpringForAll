package com.jr.basic.meta.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zcx
 * @Date: 2018/10/23 09:28
 * @Description: 分页信息
 */
@Data
public class Page<T> implements Serializable {

    private Long total;

    private boolean pageable = false;

    private Integer curPage;

    private Integer pageCount = Integer.valueOf(0);

    private Integer max = Integer.valueOf(15);

    private Iterable<T> datas;

    public void refresh() {
        this.pageCount = (int)(this.total.longValue() / (long)this.max.intValue());
        if (this.total.longValue() % (long)this.max.intValue() != 0L) {
            Integer var1 = this.pageCount;
            Integer var2 = this.pageCount = this.pageCount.intValue() + 1;
        }

        this.curPage = Math.min(this.curPage.intValue(), this.pageCount.intValue());
        this.curPage = Math.max(this.curPage.intValue(), 1);
    }
    public Page<T> setTotal(Long total) {
        this.total = total;
        this.refresh();
        return this;
    }
    public int getOffset() {
        return (this.curPage.intValue() - 1) * this.max.intValue();
    }

    public Page<T> setDatas(Iterable<T> datas) {
        this.datas = datas;
        return this;
    }
    public Page<T> setPageable(boolean pageable) {
        this.pageable = pageable;
        return this;
    }
}
