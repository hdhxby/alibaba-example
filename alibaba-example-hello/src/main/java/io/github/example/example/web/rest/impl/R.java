package io.github.example.example.web.rest.impl;

public class R {

    private Integer code;
    private P data;

    public R(Integer code, P data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public P getData() {
        return data;
    }

    public void setData(P data) {
        this.data = data;
    }
}
