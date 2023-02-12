package com.jwh.job.service.schedule;

import com.jwh.job.common.PointLevel;
import com.jwh.job.entity.TaskCronConfig;
import com.jwh.job.entity.TaskEntity;
import com.jwh.job.entity.timeWheel.TimeWheelEntity;
import com.jwh.job.entity.timeWheel.TimeWheelLinked;
import com.jwh.job.exeception.JobException;
import com.jwh.job.service.schedule.scheduleFacotry.ScheduleCommon;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

import static com.jwh.job.common.JobExceptionEnum.TaskAddError;
import static com.jwh.job.common.JobExceptionEnum.TaskTimeConfigError;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 12:52
 */
@Component
@Data
public class TimeWheel implements Scheduler {

    private static final Integer INTERVAL = 1;

    // 时间轮启动时间
    private static final long timeWheelStartTime = System.currentTimeMillis();

    private static TimeWheelLinked [][] timeCycle = new TimeWheelLinked[3][60];

    private static Timer timer = new Timer();

    private int secondPoint;
    private int minPoint;
    private int hourPoint;
    private PointLevel pointLevel;

    @Resource(name  = "jobExecutePool")
    private ExecutorService executorService;

    private Pair<Integer, Integer> getIndex(TaskCronConfig taskCronConfig) {
        if (taskCronConfig == null) {
            throw new JobException(TaskAddError);
        }
        long time = taskCronConfig.getTimeStamp();
        // 时间设置异常
        if (time <= timeWheelStartTime) {
            throw new JobException(TaskTimeConfigError);
        }
        long moveTime = (time - timeWheelStartTime)/1000;
        int level = 0;
        long levelTime = 0L;
        while(moveTime != 0L) {
            levelTime = moveTime%60;
            moveTime /= 60;
            if (moveTime != 0L) {
                level++;
            }
        }
        return Pair.of(level, (int)levelTime);

    }


    @Override
    public void fire() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                movePoint();
                TimeWheelLinked timeWheelLinked = null;
                switch (pointLevel.getCode()) {
                    case 0: timeWheelLinked = timeCycle[pointLevel.getCode()][secondPoint];break;
                    case 1: timeWheelLinked = timeCycle[pointLevel.getCode()][minPoint];break;
                    case 2: timeWheelLinked = timeCycle[pointLevel.getCode()][hourPoint];break;
                }
                if (timeWheelLinked == null || timeWheelLinked.isEmpty()) {
                    return;
                }
                TimeWheelEntity timeWheelEntity = timeWheelLinked.getHead().getAfter();
                while(timeWheelEntity != timeWheelLinked.getTail()) {
                    TimeWheelEntity finalTimeWheelEntity = timeWheelEntity;
                    executorService.submit(() -> {
                        finalTimeWheelEntity.getTask().start();
                    });
                    timeWheelEntity = timeWheelEntity.getAfter();
                }
            }
        };
        timer.schedule(timerTask, 1000);
    }

    private void movePoint() {
        secondPoint = (secondPoint + 1) % 60;
        pointLevel = PointLevel.SECONDE;
        // 到达一分钟进位
        if (secondPoint == 0) {
            minPoint = (minPoint+1) % 60;
            pointLevel = PointLevel.MIN;
            // 到达一小时进位
            if (minPoint == 0) {
                hourPoint = (hourPoint + 1)%60;
                pointLevel = PointLevel.HOUR;
            }
        }
    }

    @Override
    public void add(TaskEntity task) {
        Pair<Integer, Integer> indexs = this.getIndex(task.getTaskCronConfig());
        TimeWheelLinked timeWheelLinked = timeCycle[indexs.getLeft()][indexs.getRight()];
        if (timeWheelLinked == null) {
            timeWheelLinked = new TimeWheelLinked();
            timeWheelLinked.addTask(task);
        } else {
            timeWheelLinked.addTask(task);
        }
        timeCycle[indexs.getLeft()][indexs.getRight()] = timeWheelLinked;
    }

    @Override
    public void remove(TaskEntity task) {
        Pair<Integer, Integer> indexs = this.getIndex(task.getTaskCronConfig());
        TimeWheelLinked timeWheelLinked = timeCycle[indexs.getLeft()][indexs.getRight()];
        // 目标位置没有初始化，因此task不存在
        if (timeWheelLinked == null) {
            return;
        } else {
            timeWheelLinked.removeTask(task);
        }
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

    @Override
    public String getScheduleName() {
        return ScheduleCommon.TIMEWHEEL.getType();
    }
}
