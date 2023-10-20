package com.jingluo.util.bean.enums;

import java.util.Arrays;
import java.util.List;

public enum ResultConfig {
    NORMAL_CONF("code","msg","data","time"),
    ;
    private final List<String> keys;

    ResultConfig(String... keys) {
        this.keys = Arrays.asList(keys);
    }

    public List<String> getKeys() {
        return keys;
    }
}
