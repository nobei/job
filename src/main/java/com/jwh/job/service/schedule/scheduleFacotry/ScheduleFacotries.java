package com.jwh.job.service.schedule.scheduleFacotry;

import com.jwh.job.common.JobExceptionEnum;
import com.jwh.job.exeception.JobException;
import com.jwh.job.service.schedule.Scheduler;
import com.jwh.job.service.schedule.TimeWheel;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 15:50
 */
@Component
public class ScheduleFacotries implements ScheduleFactory {

    @Autowired
    private List<Scheduler> schedulerList;


    @Override
    public Scheduler getSchedule(String schedule) {
        for (Scheduler scheduler : schedulerList) {
            if (schedule.equals(scheduler.getScheduleName())) {
                return scheduler;
            }
        }
        throw new JobException(JobExceptionEnum.ScheduleNotFound);
    }
}
