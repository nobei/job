package com.jwh.job.common;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 15:01
 */
public enum PointLevel {
    SECONDE(3),
    MIN(1),
    HOUR(2);

    PointLevel(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
