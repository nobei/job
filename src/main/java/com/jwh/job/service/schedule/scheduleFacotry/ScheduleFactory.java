package com.jwh.job.service.schedule.scheduleFacotry;

import com.jwh.job.service.schedule.Scheduler;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 15:48
 */
public interface ScheduleFactory {
    Scheduler getSchedule(String schedule);
}
