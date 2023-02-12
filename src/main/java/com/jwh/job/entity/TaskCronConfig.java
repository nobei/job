package com.jwh.job.entity;

import com.jwh.job.common.TimeType;
import com.jwh.job.utils.DateUtils;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 20:59
 */
@Data
public class TaskCronConfig {
    private TimeType timeType;

    private int second;
    private int min;
    private int hour;

    private long timeStamp;

    public TaskCronConfig(Long timeStamp) {
        timeType = TimeType.ABSOLUTE;
        this.timeStamp = timeStamp;
    }

    public TaskCronConfig(int second, int min, int hour) {
        timeType = TimeType.RELATIVE;
        this.second = second;
        this.min = min;
        this.hour = hour;
    }

    public long getTimeStamp() {
        if (timeStamp != 0L) {
            return timeStamp;
        } else {
            Long today =  DateUtils.getDayTimestamp(new Date());
            // 毫秒
            long taskTimeStamp = today + (this.second + this.min*60 + this.hour*60*60)*1000;
            this.timeStamp = taskTimeStamp;
            return this.timeStamp;
        }
    }

    // TODO
    public boolean judgeTime() {
        return true;
    }
}
