package com.majiang.community.service;

import com.majiang.community.dto.InformDTO;
import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.enums.InformStatusEnum;
import com.majiang.community.enums.InformTypeEnum;
import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import com.majiang.community.mapper.InformExtMapper;
import com.majiang.community.mapper.InformMapper;
import com.majiang.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformService {

    @Autowired
    private InformMapper informMapper;
    @Autowired
    private InformExtMapper informExtMapper;

    public PageQuestionDTO list(Long id, Integer page, Integer size) {

        if(page <= 0){
            page =1;
        }

        InformExample informExample = new InformExample();
        informExample.createCriteria().andInformUserIdEqualTo(id);
        Long countL = informMapper.countByExample(informExample);
        if(countL == 0){
            PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
            pageQuestionDTO.setPage(0);
            pageQuestionDTO.setEndPage(0);
            pageQuestionDTO.setPages(new ArrayList<>());
            return pageQuestionDTO;
        }
        int count = countL.intValue();
        Integer endPage = count % size ==0 ? count / size:count / size + 1;
        if(page > endPage){
            page = endPage;
        }

        Integer firstPage = page * size - size;
        InformExample informExample1 = new InformExample();
        informExample1.createCriteria().andInformUserIdEqualTo(id);
        informExample1.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(firstPage,size);

        List<Inform> informList = informMapper.selectByExampleWithRowbounds(informExample1,rowBounds);
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setPage(page,size,count);

        List<InformDTO> informDTOList = new ArrayList<>();

        if(informList != null){
            for(Inform inform:informList){
                InformDTO informDTO = new InformDTO();
                BeanUtils.copyProperties(inform,informDTO);
                informDTO.setTypeName(InformTypeEnum.getTypeName(informDTO.getType()));
                informDTOList.add(informDTO);
            }
        }

        pageQuestionDTO.setData(informDTOList);
        return pageQuestionDTO;

    }

    public Long unreadCount(Long id) {
        InformExample informExample = new InformExample();
        informExample.createCriteria().andInformUserIdEqualTo(id).andStatusEqualTo(InformStatusEnum.UNREAD.getStatus());
        return informMapper.countByExample(informExample);
    }

    public InformDTO readInform(Long id) {
        Inform inform = informMapper.selectByPrimaryKey(id);
        if(inform == null){
            throw new CustomizeException(CustomizeErrorCode.MESSAGE_NOT_FOUNT);
        }
        InformDTO informDTO = new InformDTO();
        BeanUtils.copyProperties(inform,informDTO);
        inform.setStatus(InformStatusEnum.READ.getStatus());
        informExtMapper.readInform(inform);
        return informDTO;
    }
}
