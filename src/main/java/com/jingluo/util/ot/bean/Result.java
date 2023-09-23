package com.jingluo.util.ot.bean;

import com.jingluo.util.ot.bean.enums.CodeEnum;
import com.jingluo.util.ot.bean.enums.HttpCodeEnum;

import java.util.Collection;
import java.util.Map;

/**
 * 统一格式响应结果接口
 *
 * @ClassName Result
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public interface Result<T> {
    void setData(T data);

    void setMsg(String msg);

    void setCode(String code);

    T getData();

    String getMsg();

    String getCode();

    Result<T> map(String key, T value);

    Result<T> collect(T data);

    Result<T> code(String code);

    Result<T> msg(String msg);

    Result<T> data(T data);

    Map<String, T> convertoMap(T data);

    Collection<T> covertToCollect(T data);

    <C, M> Result<T> status(CodeEnum<C, M> codeEnum);

    /**
     * 响应携带数据的成功结果
     *
     * @param data 数据
     * @param <T>  范型数据
     * @return 携带数据的结果
     */
    static <T> Result<T> success(T data) {
        Result<T> result = response(HttpCodeEnum.SERVICE_SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 响应成功结果
     *
     * @param msg 消息
     * @param <T> 范型数据
     * @return 响应统一格式成功结果
     */
    static <T> Result<T> success(String msg) {
        Result<T> result = response(HttpCodeEnum.SERVICE_SUCCESS);
        result.setMsg(msg);
        return result;
    }

    static <T, C, M> Result<T> success(CodeEnum<C, M> codeEnum) {
        return response(codeEnum);
    }

    /**
     * 响应成功结果
     *
     * @param <T> 范型的数据
     * @return 成功结果
     */
    static <T> Result<T> success() {
        return response(HttpCodeEnum.SERVICE_SUCCESS);
    }

    /**
     * 失败统一响应结果
     *
     * @param data data
     * @param <T>  范型数据
     * @return 统一响应格式数据
     */
    static <T> Result<T> error(T data) {
        Result<T> result = response(HttpCodeEnum.SERVICE_ERROR);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败结果
     *
     * @param <T>
     * @return 统一失败的格式结果
     */
    static <T> Result<T> error() {
        return response(HttpCodeEnum.SERVICE_ERROR);
    }

    /**
     * 响应失败结果
     *
     * @param msg 失败消息响应
     * @param <T> 范型数据类型
     * @return 携带失败消息的结果
     */
    static <T> Result<T> error(String msg) {
        Result<T> result = response(HttpCodeEnum.SERVICE_ERROR);
        result.setMsg(msg);
        return result;
    }


    static <T, C, M> Result<T> error(CodeEnum<C, M> codeEnum) {
        return response(codeEnum);
    }

    /**
     * 响应结果
     *
     * @param <T> 范型
     * @return 统一格式结果
     */
    static <T> Result<T> response() {
        return new HttpResult<>();
    }

    /**
     * @param codeEnum 字符枚举接口
     * @param <C>      code范型
     * @param <M>      message范型
     * @param <T>      data范型
     * @return 结果
     */
    static <C, M, T> Result<T> response(CodeEnum<C, M> codeEnum) {
        return new HttpResult<>(codeEnum);
    }
}
