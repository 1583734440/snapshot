package com.jiuzhang.snapshot.util;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description 订单号生成器
 * @Date 2021/2/7 16:29
 * @Author FU
 * NOTE: 订单号组成
 * E + 年月日时分秒(14位) + 用户ID (4位) + 0 + 4随机串
 */
public class OrderNoGenerator {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random RANDOM = new Random();
    private static final Integer RAND_NUMS = 4;

    public static void main(String[] args) {
        System.out.println(generateOrderNo(1234L));
    }

    public static String generateOrderNo(Long userId) {
        StringBuilder orderNo = new StringBuilder();
        orderNo.append("E");
        orderNo.append(FORMAT.format(new Date()));
        orderNo.append(userId);
        orderNo.append(0);
        orderNo.append(zeroLeader(RANDOM.nextInt(10000)));
        return orderNo.toString();
    }

    public static String zeroLeader(int n) {
        String nStr = String.valueOf(n);
        int nLen = nStr.length();
        int zeros = RAND_NUMS - nLen;
        StringBuilder zeroStr = new StringBuilder();
        while (zeros > 0) {
            zeroStr.append('0');
            zeros--;
        }
        return zeroStr + nStr;
    }

}
