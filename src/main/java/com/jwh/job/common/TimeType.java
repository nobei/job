package com.jwh.job.common;

import lombok.Data;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 13:24
 */
public enum TimeType {
    ABSOLUTE(1),
    RELATIVE(2);

    TimeType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }}
