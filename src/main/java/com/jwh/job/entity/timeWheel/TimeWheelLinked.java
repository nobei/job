package com.jwh.job.entity.timeWheel;

import com.google.common.collect.Maps;
import com.jwh.job.common.JobExceptionEnum;
import com.jwh.job.entity.TaskEntity;
import com.jwh.job.exeception.JobException;
import lombok.Data;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;

import java.util.Map;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 12:55
 */
@Data
public class TimeWheelLinked<T extends TaskEntity> {
    private TimeWheelEntity head;
    private TimeWheelEntity tail;
    private Map<Long, TimeWheelEntity> timeWheelEntityMap = Maps.newHashMap();

    public TimeWheelLinked() {
        head = new TimeWheelEntity();
        tail = new TimeWheelEntity();
        head.setAfter(tail);
        tail.setPre(head);
    }

    public boolean isEmpty() {
        return head.getAfter() == tail && timeWheelEntityMap.isEmpty();
    }

    public void addTask(T task) {
        if (timeWheelEntityMap.containsKey(task.getTaskId())) {
            TimeWheelEntity timeWheelEntity = timeWheelEntityMap.get(task.getTaskId());
            timeWheelEntity.setTask(task);
            return;
        }
        TimeWheelEntity t = head.getAfter();
        TimeWheelEntity timeWheelEntity = new TimeWheelEntity(task);
        head.setAfter(timeWheelEntity);
        timeWheelEntity.setPre(head);
        timeWheelEntity.setAfter(t);
        t.setPre(timeWheelEntity);
        timeWheelEntityMap.put(task.getTaskId(), timeWheelEntity);
    }

    public void removeTask(T task) {
        if (!timeWheelEntityMap.containsKey(task.getTaskId()) || this.isEmpty()) {
            throw new JobException(JobExceptionEnum.TaskAddError);
        }
        TimeWheelEntity timeWheelEntity = timeWheelEntityMap.get(task.getTaskId());
        timeWheelEntity.getPre().setAfter(timeWheelEntity.getAfter());
        timeWheelEntity.getAfter().setPre(timeWheelEntity.getPre());
        timeWheelEntityMap.remove(task.getTaskId());
    }

}
