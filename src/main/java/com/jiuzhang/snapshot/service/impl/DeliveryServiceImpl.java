package com.jiuzhang.snapshot.service.impl;

import com.jiuzhang.snapshot.constants.DeliveryType;
import com.jiuzhang.snapshot.result.DeliveryResult;
import com.jiuzhang.snapshot.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description TODO
 * @Date 2021/2/28 11:32
 * @Author FU
 */
@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {
    private Random random = new Random(47);

    @Override
    public DeliveryResult getDeliveryInfo(Integer deliveryType) {
        try {
            if (DeliveryType.express.getCode().equals(deliveryType)) {
                return new DeliveryResult((long) random.nextInt(1000), 0L, 0L);
            }
            if (DeliveryType.localDelivery.getCode().equals(deliveryType)) {
                return new DeliveryResult(0L, (long) random.nextInt(1000), (long) random.nextInt(1000));
            }
            return buildDefault();
        } catch (Exception ex) {
            // 降级策略
            log.error("获取物流信息失败 " + ex.getMessage(), ex);
            return buildDefault();
        }

    }

    private DeliveryResult buildDefault() {
        return new DeliveryResult(0L, 0L, 0L);
    }
}
