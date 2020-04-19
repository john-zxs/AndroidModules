package com.john.base.model;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2019/12/13 18:54<p>
 * Updater：<p>
 */
public class IKeyValue implements KeyValue{
    protected String key;
    protected String value;

    public IKeyValue() {
    }

    public IKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key=key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value=value;
    }

}
