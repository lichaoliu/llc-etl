package com.llc.llcetlentity.result;

import java.io.Serializable;
import java.util.HashMap;


public class Return implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 有问题 重新设计
     */
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

    public Return(String msg) {
        this.code = SUCCESS_CODE;
        this.msg = msg;
    }

    public void put(String str, Object obj) {
        if (this.content == null) {
            this.content = new HashMap<String, Object>(3);
            ((HashMap<String, Object>) this.content).put(str, obj);
        } else {
            ((HashMap<String, Object>) this.content).put(str, obj);
        }
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
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
