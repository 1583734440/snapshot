package com.jiuzhang.snapshot.result;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/2/28 10:54
 * @Author FU
 */
@Data
public class ShopResult {
    /** 店铺ID */
    private Long shopId;

    /** 店铺名称 */
    private String shopName;

    /** 是否加入担保 */
    private Boolean isSecured;

    /** 是否有线下门店 */
    private Boolean hasRetailStore;

}
