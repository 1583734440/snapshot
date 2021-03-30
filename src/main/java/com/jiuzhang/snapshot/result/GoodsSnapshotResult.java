package com.jiuzhang.snapshot.result;

import com.jiuzhang.snapshot.entity.GoodsServiceSnapshot;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Date 2021/3/1 10:46
 * @Author FU
 */
@Data
public class GoodsSnapshotResult {
    /** 订单号 */
    private String orderNo;

    /** 商品ID */
    private Long goodsId;

    /** 商品价格，分为单位 */
    private Long price;

    /** 商品标题 */
    private String goodsTitle;

    /** 商品描述 */
    private String goodsDesc;

    /** 商品服务快照列表 */
    private List<GoodsServiceSnapshot> goodsServiceSnapshots;
}
