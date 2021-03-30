package com.jiuzhang.snapshot.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 下单商品信息
 * @Date 2021/2/1 13:21
 * @Author FU
 */
@Data
public class Goods {

    /** 商品ID */
    private Long goodsId;

    /** 店铺ID */
    private Long shopId;

    /** 商品价格，以分为单位 , 前后端统一单位,避免资损故障！ */
    private Long price;

    /** 商品标题 */
    private String title;

    /** 商品描述 */
    private String desc;

    /** 商品的服务 keys */
    private String keys;

    /** 订单号 */
    private String orderNo;

    public GoodsDO toGoodsDO() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setShopId(this.shopId);
        goodsDO.setGoodsId(this.goodsId);
        goodsDO.setPrice(this.price);
        goodsDO.setTitle(this.title);
        goodsDO.setDescription(this.desc);
        goodsDO.setOrderNo(this.orderNo);
        Map map = new HashMap<>();
        map.put("ServiceKeys", keys);
        //map.put("price", price);
        goodsDO.setExtend(JSON.toJSONString(map));
        return goodsDO;
    }

}
