package com.wpc.common.enums;

/**
 * ${DESCRIPTION}
 *
 * @author wangpengcheng
 * @create 2017-05-18 13:35
 **/
public enum OperLevel {

    MAX("最高",10),//最高级别
    NORM("标准",5),//标准级别
    MIN("最低",1);//最低级别

    private String name;
    private int value;

    OperLevel(String name,int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
