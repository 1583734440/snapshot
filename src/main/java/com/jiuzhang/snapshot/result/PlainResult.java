package com.jiuzhang.snapshot.result;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/2/7 11:37
 * @Author FU
 */
@Data
public class PlainResult<T> {
    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "successful";

    private Integer code;
    private String message;
    private Boolean isSuccess;

    private T data;

    public static <T>  PlainResult<T> buildSuccess(T data) {
        PlainResult<T> succ = new PlainResult<T>();
        succ.setCode(SUCCESS_CODE);
        succ.setMessage(SUCCESS_MSG);
        succ.setIsSuccess(true);
        succ.setData(data);
        return succ;
    }

    public static  <T>  PlainResult<T>  buildFailed(Integer code, String message) {
        PlainResult<T> failed = new PlainResult<T>();
        failed.setIsSuccess(false);
        failed.setCode(code);
        failed.setMessage(message);
        failed.setData(null);
        return failed;
    }
}
