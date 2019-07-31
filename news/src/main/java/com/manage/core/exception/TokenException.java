package com.manage.core.exception;

/**
 * 业务异常
 */
public class TokenException extends RuntimeException {
    /**
     * 错误代码
     */
    protected Integer code;

    /**
     * 错误消息
     */
    protected String msg;

    public TokenException() {
    }


    /**
     * 错误消息+代码
     *
     * @param msg
     */
    public TokenException(String msg, Integer code) {
        this.msg=msg;
        this.code = code;
    }

    /**
     * 错误消息+代码+堆栈
     *
     * @param msg
     * @param code
     * @param t
     */
    public TokenException(String msg, Integer code, Throwable t) {
        super(t);
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param msg
     */
    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(String msg, Throwable t) {
        super(t);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        
          return msg;
       
    }

 
    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
 

    /**
     * 错误吗
     *
     * @return
     * @version 1.0
     */
    public Integer getCode() {
        return code;
    }
}
