package com.boot.admin.infra.exception;

/**
 * 参数正常但是无法通过业务的异常
 *
 * @author jinx
 */
public class UnprocessableException extends RuntimeException {

    public UnprocessableException(String message) {
        super(message);
    }
}
