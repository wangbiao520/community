package com.majiang.community.enums;

public enum InformStatusEnum {

    READ(0),UNREAD(1)
    ;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    InformStatusEnum(Integer status) {
        this.status = status;
    }
}
