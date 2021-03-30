package com.jiuzhang.snapshot.constants;

import lombok.Getter;

/**
 * @Description TODO
 * @Date 2021/1/27 14:40
 * @Author FU
 */
@Getter
public enum OrderState {

    book("book", 10, "下单"),
    paid("paid", 20, "支付"),
    send("send", 30, "发货"),
    close("close", 90, "关闭"),
    finished("finished", 100, "交易完成"),

    ;

    private String type;
    private Integer state;
    private String desc;

    OrderState(String type, Integer state, String desc) {
        this.type = type;
        this.state = state;
        this.desc = desc;
    }

    public static OrderState get(Integer state) {
        for (OrderState orderState: OrderState.values()) {
            if (orderState.getState().equals(state)) {
                return orderState;
            }
        }
        return null;
    }

}
