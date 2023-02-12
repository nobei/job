package com.jwh.job.entity;

import com.jwh.job.common.JobExceptionEnum;
import com.jwh.job.common.TaskStatusEnum;
import com.jwh.job.exeception.JobException;
import com.jwh.job.service.Execute;
import com.jwh.job.utils.SnowFlake;
import lombok.Builder;
import lombok.Data;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 19:20
 */
@Data
@Builder
public abstract class TaskEntity implements Execute {
    private long taskId;
    private TaskStatusEnum taskStatus;
    private TaskCronConfig taskCronConfig;

    public void start() {
        if (!TaskStatusEnum.Ready.equals(taskStatus)) {
            throw new JobException(JobExceptionEnum.TaskStatusError);
        }
        taskStatus = TaskStatusEnum.Starting;
        this.execute();
        taskStatus = TaskStatusEnum.Ready;
    }

    public void end() {
        if (TaskStatusEnum.Starting.equals(taskStatus)
                || TaskStatusEnum.Ready.equals(taskStatus)) {
            throw new JobException(JobExceptionEnum.TaskStatusError);
        }
        taskStatus = TaskStatusEnum.Finishing;
    }


    public TaskEntity(TaskCronConfig taskCronConfig) {
        this.taskCronConfig = taskCronConfig;
        taskStatus = TaskStatusEnum.Ready;
    }

    public boolean isTaskReady() {
        return TaskStatusEnum.Ready == taskStatus
                && taskCronConfig != null && taskCronConfig.judgeTime();
    }


    public long getTaskId() {
        if(taskId == 0L) {
            synchronized (TaskEntity.class) {
                if (taskId == 0L) {
                    taskId = SnowFlake.getId();
                }
            }
        }
        return taskId;
    }
}
