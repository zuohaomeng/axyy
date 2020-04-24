package com.axyy.util;

import lombok.AllArgsConstructor;

/**
 * @date 2020/4/15--14:33
 */
@AllArgsConstructor
public class RequestResult<T> {
    private static final int CODE_SUCCESS = 0;
    private static final int CODE_FAIL = -1;

    /**
     * 编码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T T;


    public static RequestResult SUCCESS() {
        return new RequestResult(CODE_SUCCESS, "成功", null);
    }
    public static RequestResult SUCCESS(String msg){
        return new RequestResult<>(CODE_SUCCESS, msg,null);
    }
    public static <T> RequestResult<T> SUCCESS(T data) {
        return new RequestResult<>(CODE_SUCCESS, "成功", data);
    }

    public static <T> RequestResult<T> SUCCESS(String msg, T data) {
        return new RequestResult<>(CODE_SUCCESS, msg, data);
    }

    public static RequestResult ERROR(String msg) {
        return new RequestResult(CODE_FAIL, msg, null);
    }

    public static <T> RequestResult<T> ERROR(T data) {
        return new RequestResult<>(CODE_FAIL, "失败", data);
    }

    public static <T> RequestResult<T> ERROR(int code, String msg, T data) {
        return new RequestResult<>(code, msg, data);
    }

    public boolean isSuccess() {
        return this.code == CODE_SUCCESS ? true : false;
    }
    public T getData(){
        return T;
    }

    public void setDate(T t) {
        T = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
