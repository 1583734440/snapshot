package com.jiuzhang.snapshot.entity;

import lombok.Data;

/**
 * @Description 服务快照配置
 * @Date 2021/1/27 14:46
 * @Author FU
 */
@Data
public class GoodsServiceSnapshotConfig {
    /** 服务的 key */
    private String key;

    /** 服务的标题 */
    private String title;

    /** 服务的描述 */
    private String desc;

    /** 服务快照配置的生效开始时间 */
    private Long start;

    /** 服务快照配置的生效结束时间 */
    private Long end;

    public GoodsServiceSnapshotConfig() {}

    public GoodsServiceSnapshotConfig(String key, String title, String desc) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.start = 0L;
        this.end = 2524579200L;
    }

    public GoodsServiceSnapshotConfig(String key, String title, String desc, Long start, Long end) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.start = start;
        this.end = end;
    }
}
