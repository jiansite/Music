package com.jester.music.enums;

/**
 * @author Jester
 */
public enum IncomeEnum {
    /** 注册 */
    REGISTER(1, "注册");
    int code;
    String msg;

    IncomeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
