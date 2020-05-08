package com.llc.llcetlentity.result;

import java.io.Serializable;


public class Return implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final Return SUCCESS = new Return("处理成功");
    public static final Return FAIL = new Return(FAIL_CODE, null);

    private int code;
    private String msg;
    private Object content;

    public Return() {
    }

    public Return(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Return(Object content) {
        this.code = SUCCESS_CODE;
        this.content = content;
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

    @Override
    public String toString() {
        return "Return{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
