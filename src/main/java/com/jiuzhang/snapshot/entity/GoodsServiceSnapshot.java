package com.jiuzhang.snapshot.entity;

import lombok.Data;

/**
 * @Description 商品服务快照信息，与前端交互的
 * @Date 2021/1/27 14:44
 * @Author FU
 */
@Data
public class GoodsServiceSnapshot {
    /** 服务的 key */
    private String key;

    /** 服务的标题 */
    private String title;

    /** 服务的描述 */
    private String desc;

}
