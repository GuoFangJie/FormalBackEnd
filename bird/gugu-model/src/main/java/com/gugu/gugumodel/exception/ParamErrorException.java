package com.gugu.gugumodel.exception;

/**
 * @author TYJ
 * 参数错误异常
 */
public class ParamErrorException extends Exception {

    private String errorMsg;

    public ParamErrorException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
