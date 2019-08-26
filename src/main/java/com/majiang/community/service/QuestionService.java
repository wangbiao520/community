package com.majiang.community.service;

import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.mapper.QuestionMapper;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PageQuestionDTO findList(Integer page, Integer size) {
        if(page <= 0){
            page =1;
        }
        Integer count = questionMapper.count();
        Integer endPage = count % size ==0 ? count / size:count / size + 1;
        if(page > endPage){
            page = endPage;
        }
        Integer firstPage = page * size - size;
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.findPage(firstPage,size);
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setPage(page,size,count);

        for(Question question:questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectById(question.getCreator());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageQuestionDTO.setQuestionDTOList(questionDTOList);
        return pageQuestionDTO;

    }
}
