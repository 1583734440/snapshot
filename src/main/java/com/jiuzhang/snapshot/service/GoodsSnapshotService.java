package com.jiuzhang.snapshot.service;

import com.jiuzhang.snapshot.entity.BookInfo;
import com.jiuzhang.snapshot.query.GoodsSnapshotQuery;
import com.jiuzhang.snapshot.result.GoodsSnapshotResult;

/**
 * @Description TODO
 * @Date 2021/2/7 11:56
 * @Author FU
 */
public interface GoodsSnapshotService {

    public Boolean save(BookInfo bookInfo);

    GoodsSnapshotResult detail(GoodsSnapshotQuery goodsSnapshotQuery);
}
