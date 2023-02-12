package com.jwh.job.entity.timeWheel;

import com.jwh.job.entity.TaskEntity;
import lombok.Data;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 12:53
 */
@Data
public class TimeWheelEntity<T extends TaskEntity> {
    private TimeWheelEntity pre;
    private TimeWheelEntity after;
    private T task;

    public TimeWheelEntity() {
    }

    public TimeWheelEntity(T task) {
        this.task = task;
        pre = null;
        after = null;
    }
}
