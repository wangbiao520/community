package com.majiang.community.dto;

import com.majiang.community.enums.InformTypeEnum;
import lombok.Data;

@Data
public class InformDTO {

    private Long id;
    private Integer status;
    private String typeName;
    private Integer type;
    private Long gmtCreate;
    private Long informUserId;
    private Long informId;
    private String informTitle;
    private Long beginUserId;
    private String beginUserName;

}
