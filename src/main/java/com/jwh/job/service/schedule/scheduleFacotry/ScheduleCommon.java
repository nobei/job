package com.jwh.job.service.schedule.scheduleFacotry;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2023/2/12 15:58
 */
public enum ScheduleCommon {
    TIMEWHEEL("TimeWheel"),
    LOOP("Loop");

    ScheduleCommon(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }}
