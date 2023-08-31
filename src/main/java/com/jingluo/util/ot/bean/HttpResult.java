package com.jingluo.util.ot.bean;

import com.jingluo.util.ot.bean.enums.CodeEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * http结果封装
 *
 * @ClassName HttpResult
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
@SuppressWarnings("all")
public class HttpResult<T> implements Serializable, Result<T> {
    private String code;
    private String msg;
    private T data;

    public HttpResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public HttpResult(CodeEnum codeEnum) {
        this.code = String.valueOf(codeEnum.getCode());
        this.msg = String.valueOf(codeEnum.getMsg());
    }

    public HttpResult() {
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Result<T> map(String key, T value) {
        if (this.data == null) {
            this.data = (T) new HashMap<String, T>();
        }
        if (this.data instanceof Map) {
            Map<String, T> map = (Map<String, T>) this.data;
            map.put(key, value);
            return this;
        }
        if (this.data instanceof Collection) {
            Collection<T> collection = covertToCollect(this.data);
            HashMap<String, T> map = new HashMap<>();
            map.put(key, value);
            collection.add((T) map);
            return this;
        }
        return this;
    }

    @Override
    public Result<T> collect(T data) {
        if (this.data == null) {
            this.data = (T) new ArrayList<T>();
        }
        if (this.data instanceof Collection) {
            Collection<T> collection = covertToCollect(this.data);
            collection.add(data);
        }
        return this;
    }

    @Override
    public Result<T> code(String code) {
        this.code = code;
        return this;
    }

    @Override
    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public Result<T> data(T data) {
        if(this.data == null){
            this.data = data;
            return this;
        }
        if (this.data == null || this.data instanceof Map) {
            Map<String, T> map = convertoMap(this.data);
            map.put(data.getClass().getSimpleName(), data);
            return this;
        }
        if (this.data == null || this.data instanceof Collection) {
            Collection<T> collection = covertToCollect(this.data);
            collection.add(data);
            return this;
        }
        return this;
    }

    @Override
    public Map<String, T> convertoMap(T data) {
        if (data == null) {
            return new HashMap<>();
        }
        if (data instanceof Map) {
            return (Map<String, T>) data;
        }
        return null;
    }

    @Override
    public Collection<T> covertToCollect(T data) {
        if (data == null) {
            return new ArrayList<>();
        }
        if (data instanceof Collection) {
            return (Collection<T>) data;
        }
        return null;
    }

    @Override
    public Result<T> status(CodeEnum codeEnum) {
        this.code = String.valueOf(codeEnum.getCode());
        this.msg = String.valueOf(codeEnum.getMsg());
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}

