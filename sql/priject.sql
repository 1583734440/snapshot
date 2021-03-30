CREATE USER 'trade'@'localhost' IDENTIFIED BY 'trade';

CREATE DATABASE trade;

GRANT ALL PRIVILEGES ON trade.* to 'trade'@'localhost' IDENTIFIED BY 'trade';

USE trade;

CREATE TABLE trade_order
(
    id            int(10) UNSIGNED  NOT NULL primary key AUTO_INCREMENT COMMENT '数据库主键',
    order_no      varchar(64)       NOT NULL COMMENT '订单号',
    book_time     int(10) UNSIGNED  NOT NULL COMMENT '下单时间，秒为单位',
    shop_id       int(10) UNSIGNED  NOT NULL COMMENT '下单店铺',
    user_id       int(10) UNSIGNED  NOT NULL COMMENT '下单用户',
    state         smallint UNSIGNED NOT NULL COMMENT '订单状态',
    delivery_type smallint UNSIGNED NOT NULL COMMENT '配送方式',
    pay_way       smallint UNSIGNED NOT NULL COMMENT '支付方式',
    price         int(10) UNSIGNED  NOT NULL COMMENT '订单金额',
    extend        varchar(1000) DEFAULT '{}' COMMENT '订单扩展信息',

    gmt_create    DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify    DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',

    unique key (order_no)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE trade_goods
(
    id          int(10) UNSIGNED NOT NULL primary key AUTO_INCREMENT COMMENT '数据库主键',
    goods_id    int(10) UNSIGNED NOT NULL COMMENT '商品ID',
    shop_id     int(10) UNSIGNED NOT NULL COMMENT '店铺ID',
    price       int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品价格',
    title       varchar(256)     NOT NULL COMMENT '商品名称',
    order_no    varchar(64)      NOT NULL COMMENT '订单号',
    description varchar(512)     NOT NULL COMMENT '商品描述',
    extend      varchar(1000)             DEFAULT '{}' COMMENT '商品扩展信息',

    gmt_create  DATETIME                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modify  DATETIME                  DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

