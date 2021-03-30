package com.jiuzhang.snapshot.service;

import com.jiuzhang.snapshot.result.DeliveryResult;

/**
 * @Description TODO
 * @Date 2021/2/28 11:31
 * @Author FU
 */
public interface DeliveryService {
    DeliveryResult getDeliveryInfo(Integer deliveryType);
}
