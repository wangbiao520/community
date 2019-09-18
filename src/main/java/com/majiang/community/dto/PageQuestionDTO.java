package com.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageQuestionDTO<T> {

    private boolean showNextPage;
    private boolean showLastPage;
    private boolean showFirstPage;
    private boolean showEndPage;
    private List<T> data;
    private List<Integer> pages = new ArrayList<>();
    private Integer page;
    private Integer endPage;

    public void setPage(Integer page,Integer size, Integer count){
        endPage = count % size ==0 ? count / size:count / size + 1;
        if(page > endPage){
            page = endPage;
        }
        this.page = page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            //计算page左边
            if(page - i >0){
                pages.add(0,page -i);
            }
            //计算page右边
            if(page + i <= endPage){
                pages.add(page + i);
            }
        }
        //计算上一页
        if(page ==1){
            showLastPage = true;
        }
        //计算下一页
        if(page ==endPage){
            showNextPage = true;
        }
        //计算第一页
        if(pages.get(0) == 1){
            showFirstPage = true;
        }
        //计算最后一页
        if(pages.get(pages.size()-1) == endPage){
            showEndPage = true;
        }
    }

}
