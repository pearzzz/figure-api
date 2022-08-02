package com.red.figureapi.exception;

import lombok.Data;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 13:35 2022-08-02
 */
@Data
public class FigureException extends RuntimeException {
    /**
     * 异常信息
     */
    private String msg;
    /**
     * 状态码
     */
    private int code = 500;

    public FigureException(String msg) {
        this.msg = msg;
    }

    public FigureException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public FigureException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public FigureException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
