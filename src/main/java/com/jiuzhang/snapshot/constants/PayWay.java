package com.jiuzhang.snapshot.constants;

import lombok.Getter;

/**
 * @Description TODO
 * @Date 2021/1/27 14:40
 * @Author FU
 */
@Getter
public enum PayWay {

    wxpay("wxpay", 0, "微信支付"),
    alipay("alipay", 1, "支付宝支付"),
    codpay("codpay", 9, "货到付款");

    private String type;
    private Integer code;
    private String desc;

    PayWay(String type, Integer code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    public static PayWay get(Integer code) {
        for (PayWay payWay: PayWay.values()) {
            if (payWay.code.equals(code)) {
                return payWay;
            }
        }
        return null;
    }


}
