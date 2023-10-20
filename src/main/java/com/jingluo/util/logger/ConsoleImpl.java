package com.jingluo.util.logger;

/**
 * 详细介绍类的情况.
 *
 * @ClassName LoggerImpl
 * @Author oldTree
 * @Date 2023/8/22
 * @Version 1.0
 */
public final class ConsoleImpl implements Console {
    @Override
    public void print(String message) {
        System.out.print(message);
    }
    @Override
    public void println(String message) {
        System.out.println(message);
    }
}

