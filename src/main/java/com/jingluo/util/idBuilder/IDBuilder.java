package com.jingluo.util.idBuilder;

import java.util.UUID;

/**
 * id生成工具
 *
 * @ClassName Id
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public interface IDBuilder {

    long creId();
    long defaultWordId = 1L;
    long defaultDatacenterId = 0L;
    SnowflakeIdWorker snowFlakeIdWorker = new SnowflakeIdWorker(defaultWordId,defaultDatacenterId);
    /**
     * 雪花id
     * @return 雪花id
     */
    static long snowId() {
        return snowFlakeIdWorker.creId();
    }
    static String uuID(){
        return UUID.randomUUID().toString();
    }
}
