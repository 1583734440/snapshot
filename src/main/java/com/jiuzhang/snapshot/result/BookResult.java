package com.jiuzhang.snapshot.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Date 2021/2/7 15:35
 * @Author FU
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResult {

    /** 下单生成的订单号 */
    private String orderNo;

    /** 商品ID */
    private Long goodsId;

}
