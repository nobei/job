package com.jwh.job.service;

import com.jwh.job.entity.TaskEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 12:45
 */
@Component
@Data
public class TaskContext {
    @Autowired
    List<TaskEntity> taskEntities;
}
