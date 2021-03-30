package com.jiuzhang.snapshot.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/2/28 10:56
 * @Author FU
 */
@Data
@AllArgsConstructor
public class DeliveryResult {
    /** 快递运费 */
    private Long expressFee;

    /** 同城送起送金额 */
    private Long localDeliveryStartPrice;

    /** 同城送配送金额 */
    private Long localDeliveryPrice;
}
