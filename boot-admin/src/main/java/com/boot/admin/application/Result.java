package com.boot.admin.application;

import lombok.Getter;

/**
 * 统一响应模型
 *
 * @author Jinx
 **/
@Getter
public class Result<T> {
    /**
     * 响应code
     */
    private int code = 200;
    /**
     * 提示信息
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    private Result() {
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }
}
