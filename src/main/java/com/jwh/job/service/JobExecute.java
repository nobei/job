package com.jwh.job.service;

import com.jwh.job.service.schedule.ScheduleThread;
import com.jwh.job.service.schedule.Scheduler;
import com.jwh.job.service.schedule.scheduleFacotry.ScheduleFacotries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 19:58
 */
@Component
public class JobExecute {

    @Autowired
    private ScheduleThread scheduleThread;

    @Autowired
    private TaskContext taskContext;

    @Autowired
    private ScheduleFacotries facotries;

    @Value("schedule")
    private String scheduleName;

    private Scheduler scheduler;

    @PostConstruct
    public void init() {
        scheduler = facotries.getSchedule(scheduleName);
    }

    public void startSchedule() {
        scheduler.fire();
    }

}
