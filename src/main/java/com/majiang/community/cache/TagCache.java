package com.majiang.community.cache;

import com.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {

    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO language = new TagDTO();
        language.setType("开发语言");
        language.setName(Arrays.asList("java","php","c","js"));
        tagDTOS.add(language);
        TagDTO server = new TagDTO();
        server.setType("操作系统");
        server.setName(Arrays.asList("windows","linux","redhat","UNIX","Mac OS"));
        tagDTOS.add(server);
        return tagDTOS;
    }

    public static String errorTag(String tag) {

        //获取tag
        List<TagDTO> tagDTOS = get();
        //将传来tag转数组
        String[] split = StringUtils.split(tag, ",");
        //拿到tagName
        List<String> collect = tagDTOS.stream().flatMap(tagDTO -> tagDTO.getName().stream()).collect(Collectors.toList());
        //对比
        String errorTag = Arrays.stream(split).filter(t -> !collect.contains(t)).collect(Collectors.joining(","));
        return errorTag;
    }
}
