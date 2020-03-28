package com.suneee.constant;

public enum LogisticalEnum {
    ZTO("中通快递"),YTO("圆通快递"),STO("申通快递"),JD("京东快递");

    String name;

    LogisticalEnum(String name){
        this.name=name;
    }
}
