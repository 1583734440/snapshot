package com.jiuzhang.snapshot.mapper;

import com.jiuzhang.snapshot.entity.OrderDO;
import com.jiuzhang.snapshot.query.OrderQuery;

/**
 * @Description TODO
 * @Date 2021/2/1 14:37
 * @Author FU
 */
public interface OrderMapper {

    public Integer insert(OrderDO orderDO);

    OrderDO query(OrderQuery orderQuery);
}
