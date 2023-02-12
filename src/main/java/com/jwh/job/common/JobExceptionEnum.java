package com.jwh.job.common;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 21:28
 */
public enum JobExceptionEnum {
    SystemError(-1, "sytemError"),
    TaskStatusError(1,"taskStatusError"),
    TaskAddError(2,"TaskAddError"),
    TaskRemoveError(3, "TaskRemoveError"),
    TaskTimeConfigError(4, "TaskTimeConfigError"),
    ScheduleNotFound(5, "ScheduleNotFound");


    JobExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private String msg;
}
