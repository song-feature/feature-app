package com.songxu.feature.api.model.exception;


/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 11:10:53
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 3045341956749328443L;

    public CommonException(String message) {
        super(message);
    }
}
