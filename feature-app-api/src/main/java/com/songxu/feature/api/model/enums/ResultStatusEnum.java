package com.songxu.feature.api.model.enums;

/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 11:06:44
 */
public enum ResultStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    ERROR(500, "fail");


    private final Integer value;

    private final String msg;

    ResultStatusEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer value() {
        return value;
    }

    public String msg() {
        return msg;
    }
}
