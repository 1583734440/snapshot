package com.jiuzhang.snapshot.query;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/3/1 10:51
 * @Author FU
 */
@Data
public class OrderQuery {

    private String orderNo;

    private Long shopId;

    private Long userId;

    private Long bookTime;
}
