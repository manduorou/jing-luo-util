package com.jingluo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 详细介绍类的情况.
 *
 * @ClassName JLPage
 * @Author 鲸落网络-oldTree
 * @Date 2023/9/29
 * @Version 1.0
 */
public class JLPage<T> implements IPage<T>,Serializable {
    protected List<T> records;
    protected long total;
    protected long size;
    protected long current;
    protected List<OrderItem> orders;
    protected boolean optimizeCountSql;
    protected boolean searchCount;
    protected boolean optimizeJoinOfCountSql;
    protected Long maxLimit;
    protected String countId;

    public JLPage() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList<>();
        this.optimizeCountSql = true;
        this.searchCount = true;
        this.optimizeJoinOfCountSql = true;
    }
    public JLPage(IPage<T> page){
        this.records = page.getRecords();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.total = page.getTotal();
        this.countId = page.countId();
        this.maxLimit = page.maxLimit();
        this.optimizeCountSql = page.optimizeCountSql();
        this.searchCount = page.searchCount();
        this.orders = page.orders();
    }

    @Override
    public List<OrderItem> orders() {
        return orders;
    }

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isOptimizeJoinOfCountSql() {
        return optimizeJoinOfCountSql;
    }

    public void setOptimizeJoinOfCountSql(boolean optimizeJoinOfCountSql) {
        this.optimizeJoinOfCountSql = optimizeJoinOfCountSql;
    }

    public Long getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Long maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }
}

