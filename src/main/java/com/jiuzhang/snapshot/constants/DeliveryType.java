package com.jiuzhang.snapshot.constants;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Date 2021/1/27 14:36
 * @Author FU
 */
@Getter
public enum DeliveryType {

    express("express", 0, "快递发货"),
    selfetch("selfetch", 1 , "到店自提"),
    localDelivery("localDelivery", 2, "同城配送"),

    ;

    private String delivery;
    private Integer code;
    private String desc;

    DeliveryType(String delivery, Integer code, String desc) {
        this.delivery = delivery;
        this.code = code;
        this.desc = desc;
    }

    public static DeliveryType get(Integer deliveryType) {
        for (DeliveryType type: DeliveryType.values()) {
            if (type.getCode().equals(deliveryType)) {
                return type;
            }
        }
        return null;
    }

}
