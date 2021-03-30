package com.jiuzhang.snapshot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jiuzhang.snapshot.mapper")
@SpringBootApplication
public class SnapshotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnapshotApplication.class, args);
    }

}
