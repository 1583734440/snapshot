package com.jiuzhang.snapshot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiuzhang.snapshot.entity.*;
import com.jiuzhang.snapshot.mapper.GoodsMapper;
import com.jiuzhang.snapshot.mapper.OrderMapper;
import com.jiuzhang.snapshot.query.GoodsQuery;
import com.jiuzhang.snapshot.query.GoodsSnapshotQuery;
import com.jiuzhang.snapshot.query.OrderQuery;
import com.jiuzhang.snapshot.result.GoodsSnapshotResult;
import com.jiuzhang.snapshot.service.GoodsSnapshotService;
import com.jiuzhang.snapshot.snapshot.GoodsServiceSnapshotProcessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 商品快照服务实现
 * @Date 2021/2/7 11:56
 * @Author FU
 */
@Service
public class GoodsSnapshotServiceImpl implements GoodsSnapshotService {
    @Resource
    GoodsMapper goodsMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    private GoodsServiceSnapshotProcessor goodsServiceSnapshotProcessor;

    @Override
    @Transactional
    public Boolean save(BookInfo bookInfo) {
        GoodsDO goodsDO = bookInfo.getGoods().toGoodsDO();
        goodsMapper.insert(goodsDO);

        OrderDO orderDO = bookInfo.getOrder().toOrderDO();
        orderMapper.insert(orderDO);
        return true;
    }

    @Override
    public GoodsSnapshotResult detail(GoodsSnapshotQuery goodsSnapshotQuery) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderNo(goodsSnapshotQuery.getOrderNo());
        OrderDO orderDO = orderMapper.query(orderQuery);
        Order order = Order.from(orderDO);

        GoodsQuery goodsQuery = new GoodsQuery();
        goodsQuery.setGoodsId(goodsSnapshotQuery.getGoodsId());
        goodsQuery.setOrderNo(goodsSnapshotQuery.getOrderNo());
        GoodsDO goodsDO = goodsMapper.query(goodsQuery);

        GoodsSnapshotResult goodsSnapshotResult = new GoodsSnapshotResult();
        goodsSnapshotResult.setGoodsId(goodsDO.getGoodsId());
        goodsSnapshotResult.setGoodsTitle(goodsDO.getTitle());
        goodsSnapshotResult.setGoodsDesc(goodsDO.getDescription());
        goodsSnapshotResult.setOrderNo(orderDO.getOrderNo());
        JSONObject jsonObject = JSON.parseObject(goodsDO.getExtend());
        //Long price = jsonObject.getLong("price");
        goodsSnapshotResult.setPrice(goodsDO.getPrice());
        String keysStr = jsonObject.getString("ServiceKeys");
        List<String> keys = Arrays.asList(keysStr.split(","));

        List<GoodsServiceSnapshot> goodsServiceSnapshots = goodsServiceSnapshotProcessor.get(order, keys);
        goodsSnapshotResult.setGoodsServiceSnapshots(goodsServiceSnapshots);
        return goodsSnapshotResult;
    }
}
