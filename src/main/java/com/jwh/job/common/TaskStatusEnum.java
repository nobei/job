package com.jwh.job.common;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/11 19:22
 */
public enum TaskStatusEnum {

    Ready(0, "ready"),
    Starting(1, "starting"),
    Finishing(2, "Finishing"),
    Pause(-1, "Pause");


    TaskStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private String status;

}
