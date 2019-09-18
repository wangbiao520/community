package com.majiang.community.enums;

public enum InformTypeEnum {

    REPLY(0,"回复了您的问题"),COMMENT(1,"评论了您的问题");

    private Integer type;
    private String typeName;

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    InformTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static String getTypeName(Integer informTypeEnum){

        for(InformTypeEnum typeEnum:InformTypeEnum.values()){
            if(typeEnum.getType() == informTypeEnum){
                return typeEnum.getTypeName();
            }
        }
        return "";

    }

}
