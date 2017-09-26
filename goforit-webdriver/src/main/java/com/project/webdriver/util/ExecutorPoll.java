package com.project.webdriver.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ExecutorPoll {

    public static ThreadPoolExecutor creatNamedFixedPools(String name, int size) {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(size, new ThreadFactoryBuilder().setNameFormat(name).build());
    }

    public static ThreadPoolExecutor createNamedCachePools(String name) {
        return (ThreadPoolExecutor) Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat(name).build());
    }

    public static ThreadPoolExecutor createNamedSinglePools(String name) {
        return (ThreadPoolExecutor) Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat(name).build());
    }

}
