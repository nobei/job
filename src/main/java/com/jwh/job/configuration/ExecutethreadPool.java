package com.jwh.job.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 14:34
 */
@Configuration
public class ExecutethreadPool {
    @Value("core")
    private int coreThreadNum;

    @Bean("jobExecutePool")
    public ExecutorService jobTreadPool() {
        return Executors.newFixedThreadPool(coreThreadNum);
    }
}
