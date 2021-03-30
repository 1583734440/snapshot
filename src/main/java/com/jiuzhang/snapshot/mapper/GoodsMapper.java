package com.jiuzhang.snapshot.mapper;

import com.jiuzhang.snapshot.entity.GoodsDO;
import com.jiuzhang.snapshot.query.GoodsQuery;

/**
 * @Description TODO
 * @Date 2021/2/1 14:44
 * @Author FU
 */
public interface GoodsMapper {

    public Integer insert(GoodsDO goodsDO);

    GoodsDO query(GoodsQuery goodsQuery);
}
