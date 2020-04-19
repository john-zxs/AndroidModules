package com.john.base.interfaces;

public interface ISP {
     /**
     * 存储的文件
     *
     * @param fileName 文件名
     * @return
     */
    public ISP file(String fileName);

    /**
     * 存储数据
     *
     * @param name
     * @param value
     */
    public void save(String name, String value);

    /**
     * 取数据
     *
     * @param name
     * @return
     */
    public String get(String name);
}
