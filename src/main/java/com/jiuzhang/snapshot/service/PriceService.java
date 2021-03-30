package com.jiuzhang.snapshot.service;

import com.jiuzhang.snapshot.entity.BookInfo;

/**
 * @Description TODO
 * @Date 2021/2/28 10:44
 * @Author FU
 */
public interface PriceService {

    Long calcPrice(BookInfo bookInfo);
}
