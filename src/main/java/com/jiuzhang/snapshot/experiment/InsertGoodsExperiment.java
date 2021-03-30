package com.jiuzhang.snapshot.experiment;

import com.alibaba.fastjson.JSON;
import com.jiuzhang.snapshot.entity.GoodsDO;
import com.jiuzhang.snapshot.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2021/2/1 15:23
 * @Author FU
 */
@Slf4j
//@Component
public class InsertGoodsExperiment implements IExperiment {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public void test() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodsId(123456L);
        goodsDO.setShopId(654321L);
        goodsDO.setPrice(1999L);
        goodsDO.setTitle("认养一头牛");
        goodsDO.setDescription("纯天然奶牛场");
        goodsDO.setOrderNo("E202009010000001234000103");
        Map map = new HashMap<>();
        map.put("category", "食品");
        goodsDO.setExtend(JSON.toJSONString(map));
        Integer insertId = goodsMapper.insert(goodsDO);
        log.info("Goods insertId: " + insertId);
    }
}
