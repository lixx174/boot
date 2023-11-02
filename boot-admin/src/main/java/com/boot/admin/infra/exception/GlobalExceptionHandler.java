package com.boot.admin.infra.exception;

import com.boot.admin.application.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jinx
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    final Environment environment;

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Void> unprocessableEntity(Exception e) {
        return handle(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> validException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getFieldErrors().get(0);
        return handle(
                HttpStatus.BAD_REQUEST,
                e,
                fieldError.getDefaultMessage() + " [%s]".formatted(fieldError.getRejectedValue())
        );
    }

    @ExceptionHandler({
            UnsupportedOperationException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> badRequestException(Exception e) {
        return handle(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> exception(Exception e) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    /**
     * 日志处理
     *
     * @param httpStatus 响应状态码
     * @param e          源异常
     * @param tips       异常友好提示
     * @return Result
     */
    private Result<Void> handle(HttpStatus httpStatus, Exception e, String tips) {
        log.error(tips, e);

        return Result.error(httpStatus.value(), tips);
    }

    private Result<Void> handle(HttpStatus httpStatus, Exception e) {
        return handle(httpStatus, e, e.getMessage());
    }
}