package com.jwh.job.utils;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author
 * @version 1.0
 * @date
 */
public class DateUtils {

    private static final long MILLIS_PER_DAY = 86400000L;
    private static final long TIME_ZONE_OFFSET = TimeZone.getDefault().getRawOffset();

    /**
     * 获取指定时间当日 00:00:00 时刻的时间戳
     */
    public static Long getDayTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        /* 距离当日 00:00:00 时刻毫秒数 */
        long dayOffsetTimestamp = (date.getTime() + TIME_ZONE_OFFSET) % MILLIS_PER_DAY;
        return date.getTime() - dayOffsetTimestamp;
    }

    private DateUtils () {}
}