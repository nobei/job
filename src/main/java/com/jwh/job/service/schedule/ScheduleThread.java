package com.jwh.job.service.schedule;

import com.jwh.job.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 20:47
 */
@Component
public class ScheduleThread implements Scheduler {


    @Override
    public void fire() {

    }

    @Override
    public void add(TaskEntity task) {

    }

    @Override
    public void remove(TaskEntity task) {

    }

    @Override
    public void cancel() {

    }
}
