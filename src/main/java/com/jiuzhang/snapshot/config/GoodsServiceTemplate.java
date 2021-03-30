package com.jiuzhang.snapshot.config;

import com.alibaba.fastjson.JSON;
import com.jiuzhang.snapshot.entity.GoodsServiceSnapshotConfig;
import com.jiuzhang.snapshot.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sun.jmx.mbeanserver.Util.cast;

/**
 * @Description 服务快照配置集合
 * @Date 2021/1/27 14:48
 * @Author FU
 */
@Slf4j
@Component
public class GoodsServiceTemplate {

    private List<GoodsServiceSnapshotConfig> goodsServiceSnapshotConfigList = new ArrayList<>();

    private Map<String, List<GoodsServiceSnapshotConfig>> configMap;

    @Value("classpath:service1.json")
    private Resource fileResource1;

    private WatchService watchService;
    @PostConstruct
    public void init() {
        try {
            // 加载 商品服务 配置信息
            convertFileToList();
            // 打印日志信息
            log.info(JSON.toJSONString(goodsServiceSnapshotConfigList));
            log.info(configMap.toString());
            // 创建监听器
            watchService = FileSystems.getDefault().newWatchService();
            // 设置监听文件 和 监听事件
            Paths.get(fileResource1.getFile().getParent()).register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // 开启监听 配置文件修改
            new Thread(() -> listenFileModified()).start();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getCause());  // 异常转译
        }
    }

    /**
     * 读取文件内容， 转换成 list、map
     *
     * @throws IOException
     */
    private void convertFileToList() throws IOException {
        File file = fileResource1.getFile();
        String content = FileUtil.readFile(file);
        goodsServiceSnapshotConfigList = JSON.parseArray(content, GoodsServiceSnapshotConfig.class);
        configMap = convert(goodsServiceSnapshotConfigList);
    }

    /**
     * 监听 服务配置文件的修改
     */
    private void listenFileModified() {
        try {
         while (true) {
                WatchKey key = watchService.poll(2, TimeUnit.SECONDS);
                if (key == null) {
                    continue;
                }
                List<WatchEvent<?>> events = key.pollEvents();
                for (WatchEvent<?> event : events) {
                    if (event.kind() != StandardWatchEventKinds.ENTRY_MODIFY) {
                        continue;
                    }
                    // 监听发生变化的路径
                    Path path = cast(event.context());
                    // 监听的文件
                    String fileName = fileResource1.getFile().getName();
                }
                // 加载 商品服务 配置信息
                convertFileToList();
                // 清空 key
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 list 转换成 map
     *
     * @param goodsServiceSnapshotConfigs
     * @return
     */
    private Map<String, List<GoodsServiceSnapshotConfig>> convert(List<GoodsServiceSnapshotConfig> goodsServiceSnapshotConfigs) {
        Map<String, List<GoodsServiceSnapshotConfig>> goodsServiceSnapshotConfigMap = new HashMap<>();
        for (GoodsServiceSnapshotConfig config : goodsServiceSnapshotConfigs) {
            if (!goodsServiceSnapshotConfigMap.containsKey(config.getKey())) {
                goodsServiceSnapshotConfigMap.put(config.getKey(), new ArrayList<>());
            }
            goodsServiceSnapshotConfigMap.get(config.getKey()).add(config);
        }
        return goodsServiceSnapshotConfigMap;
    }

    /**
     * 根据 key 换取所有的服务配置
     *
     * @param key
     * @return
     */
    public List<GoodsServiceSnapshotConfig> getBy(String key) {
        return configMap.get(key);
    }
}
