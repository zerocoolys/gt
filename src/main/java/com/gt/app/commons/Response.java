package com.gt.app.commons;

/**
 * Response
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class Response {

    private boolean success;
    private String msg;

    private Object data;

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
