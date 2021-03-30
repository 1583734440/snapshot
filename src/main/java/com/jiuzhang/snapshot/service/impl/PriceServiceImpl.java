package com.jiuzhang.snapshot.service.impl;

import com.jiuzhang.snapshot.entity.BookInfo;
import com.jiuzhang.snapshot.exception.Errors;
import com.jiuzhang.snapshot.exception.TradeSnapshotException;
import com.jiuzhang.snapshot.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description TODO
 * @Date 2021/2/28 10:45
 * @Author FU
 */
@Service
public class PriceServiceImpl implements PriceService {

    private static Random random = new Random();

    @Override
    public Long calcPrice(BookInfo bookInfo) {
        Long value= (long) random.nextInt(10);
        if (value == 0) {
            throw new TradeSnapshotException(Errors.PriceCalcError);
        }
        return value;
    }
}
