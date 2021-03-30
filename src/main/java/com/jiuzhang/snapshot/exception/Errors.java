package com.jiuzhang.snapshot.exception;

import lombok.Getter;

/**
 * @Description TODO
 * @Date 2021/2/28 14:48
 * @Author FU
 */
@Getter
public enum Errors implements IError {

    ServerError(500, "服务器异常"),
    PriceCalcError(1000011, "价格计算错误"),
    ShopInfoError(100012, "获取店铺信息失败"),

    ;

    private Integer errorCode;
    private String errorMessage;

    Errors(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
