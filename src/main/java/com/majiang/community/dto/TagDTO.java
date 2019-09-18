package com.majiang.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {

    private String type;
    private List<String> name;

}
