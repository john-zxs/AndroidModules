package com.john.common.sharedpreference;

public enum SPFileName {
    //数据库
    DB("datebase"), DB_TABLE("table1"),
    //用户
    USERINFO("userinfo");

    public String fileName;

    SPFileName(String fileName) {
        this.fileName = fileName;
    }

}
