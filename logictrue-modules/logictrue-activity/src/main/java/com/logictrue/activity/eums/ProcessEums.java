package com.logictrue.activity.eums;

public enum ProcessEums implements ExceptionEnums{
    purchase(new Long(5), "purchase");

    private Long code;
    private String msg;

    ProcessEums(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
