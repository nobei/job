package com.jwh.job.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 19:45
 */
public class SnowFlake {

    private static Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    public static long getId() {
        return snowflake.nextId();
    }
}
