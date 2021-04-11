package com.songxu.feature.api.model;

import com.songxu.feature.api.model.enums.ResultStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 11:03:48
 */
@Data
public class Result <T> implements Serializable {
    private static final long serialVersionUID = -6093401570916666734L;

    private T data;

    private Integer code;

    private String errorMsg;


    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(ResultStatusEnum.SUCCESS.value());
        result.setErrorMsg("");
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static Result panic(String message) {
        Result result = new Result();
        result.setCode(ResultStatusEnum.ERROR.value());
        result.setErrorMsg(message);
        return result;
    }
}
