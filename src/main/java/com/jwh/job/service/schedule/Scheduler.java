package com.jwh.job.service.schedule;

import com.jwh.job.entity.TaskEntity;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 12:48
 */
public interface Scheduler<T extends TaskEntity> {
    /**
     * 开始工作
     */
    void fire();
    /**
     * 增加任务
     * @param task
     * @return
     */
    void add(T task);

    /**
     * 删除任务
     * @param task
     * @return
     */
    void remove(T task);

    /**
     * 取消任务
     */
    void cancel();

    /**
     * 获取schedule名称
     * @return
     */
    String getScheduleName();
}
