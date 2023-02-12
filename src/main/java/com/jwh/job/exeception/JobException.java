package com.jwh.job.exeception;

import com.jwh.job.common.JobExceptionEnum;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 21:22
 */
public class JobException extends RuntimeException {

    private JobExceptionEnum error;

    public JobException(JobExceptionEnum error) {
        super();
        this.error = error;
    }



}
