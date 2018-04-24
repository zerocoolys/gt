package com.gt.app.utils;

import com.gt.app.commons.Response;

/**
 * Responses
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class Responses {

    public static Response ok(Object data) {
        return new Response().setSuccess(true).setData(data);
    }

    public static Response ok() {
        return new Response().setSuccess(true);
    }

    public static Response err() {
        return err("");
    }

    public static Response err(String msg) {
        return new Response().setSuccess(false).setMsg(msg);
    }
}
