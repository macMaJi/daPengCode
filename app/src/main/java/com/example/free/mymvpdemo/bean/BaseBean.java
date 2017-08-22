package com.example.free.mymvpdemo.bean;

import java.io.Serializable;


public class BaseBean implements Serializable {
    private String error_code;
    private String msg;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "error_code='" + error_code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}