package com.telan.werewolf.query;

import java.io.Serializable;

/**
 * Created by menhaihao on 2014/6/18.
 *
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 7378807577314788084L;
    protected int pageNo = 1;
    protected int pageSize = 10;
    /**
     * 如果设置这个为true，就不会返回总数，
     * 返回的list的数量会多一个，用于判断时候还有下一页 比如要15个，会给16个，如果不够16个，说明没有下一页
     * */
    protected boolean hasNextMod = false;

    protected boolean needCount = false;

    private boolean needPageQuery = false;

    public boolean getNeedPageQuery() {
        return needPageQuery;
    }

    public void setNeedPageQuery(boolean needPageQuery) {
        this.needPageQuery = needPageQuery;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public void setHasNextMod(boolean hasNextMod) {
        this.hasNextMod = hasNextMod;
    }

    public boolean isHasNextMod() {
        return hasNextMod;
    }

    public boolean isNeedCount() {
        return needCount && !hasNextMod;
    }

    public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    public int getPageSize() {
        if (hasNextMod) {
            return pageSize + 1;
        }
        return pageSize;
    }

    public int getOldPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return (pageNo - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", hasNextMod=" + hasNextMod +
                ", needCount=" + needCount +
                '}';
    }
}
