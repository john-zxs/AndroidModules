package com.john.base.model;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：<p>
 * Date：2020/2/11 15:16<p>
 * Updater：
 */
public class BaseModel<T> {

    /**
     * status : 1
     * result : null
     * msg : null
     */
    private int status;//状态值： 1：允许申报；2：不允许申报 3: 只允许 选择法定假日 申报
    private T result;
    private Object msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "status=" + status +
                ", result=" + result +
                ", msg=" + msg +
                '}';
    }
}
