package com.jiuzhang.snapshot.service.impl;

import com.jiuzhang.snapshot.exception.Errors;
import com.jiuzhang.snapshot.exception.TradeSnapshotException;
import com.jiuzhang.snapshot.result.ShopResult;
import com.jiuzhang.snapshot.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description TODO
 * @Date 2021/2/28 11:29
 * @Author FU
 */
@Service
public class ShopServiceImpl implements ShopService {
    static Random random = new Random();

    @Override
    public ShopResult getShopInfo(Long shopId) {
        int randNum = random.nextInt(200);
        if (randNum == 0) {
//             跑出去让上层感知
            throw new TradeSnapshotException(Errors.ShopInfoError);
        }
        ShopResult shopResult = new ShopResult();
        shopResult.setShopId(shopId);
        shopResult.setShopName("我的小店");
        shopResult.setHasRetailStore(random.nextBoolean());
        shopResult.setIsSecured(random.nextBoolean());
        return shopResult;
    }
}
