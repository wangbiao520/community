package com.majiang.community.service;

import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.dto.QuestionSearchDTO;
import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import com.majiang.community.mapper.QuestionExtMapper;
import com.majiang.community.mapper.QuestionMapper;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.Question;
import com.majiang.community.model.QuestionExample;
import com.majiang.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PageQuestionDTO findList(Integer page, Integer size,String search) {

        if(StringUtils.isNotBlank(search)){
            String[] split = search.split(" ");
            search = Arrays.stream(split).collect(Collectors.joining("|"));
        }

        QuestionExample questionExample = new QuestionExample();
        QuestionSearchDTO questionSearchDTO = new QuestionSearchDTO();
        questionSearchDTO.setSearch(search);
        Integer count = questionExtMapper.countBySearch(questionSearchDTO);
        //int count = countL.intValue();

        Integer firstPage = 0 ;
        Integer endPage = count % size ==0 ? count / size:count / size + 1;
        if(page > endPage){
            page = endPage;
        }
        if(page <= 0){
            page =1;
        }
        firstPage = page * size - size;

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.setOrderByClause("gmt_create desc");

        RowBounds rowBounds = new RowBounds(firstPage,size);
        questionSearchDTO.setPage(firstPage);
        questionSearchDTO.setSize(size);

        List<Question> questionList = questionExtMapper.listBySearch(questionSearchDTO);

        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setPage(page,size,count);

        for(Question question:questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageQuestionDTO.setData(questionDTOList);
        return pageQuestionDTO;

    }

    public PageQuestionDTO findList(Long id, Integer page, Integer size) {

        if(page <= 0){
            page =1;
        }
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Long countL = questionMapper.countByExample(questionExample);
        int count = countL.intValue();
        Integer endPage = count % size ==0 ? count / size:count / size + 1;
        if(page > endPage){
            page = endPage;
        }
        Integer firstPage = page * size - size;
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andCreatorEqualTo(id);
        RowBounds rowBounds = new RowBounds(firstPage,size);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample1,rowBounds);
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setPage(page,size,count);
        if(questionList != null){
            for(Question question:questionList){
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        pageQuestionDTO.setData(questionDTOList);
        return pageQuestionDTO;

    }

    public QuestionDTO findById(Long id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        if(null == question){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);

        return questionDTO;

    }

    public void createOrUpdate(Question question) {

        if(question.getId() != null){
            //更新
            question.setGmtModified(System.currentTimeMillis());
            Integer update = questionMapper.updateByPrimaryKeySelective(question);
            if(update != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }else{
            //添加
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insertSelective(question);
        }

    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<Question> findList(QuestionDTO questionDTO) {
        Question question = new Question();
        questionDTO.setTag(StringUtils.join(questionDTO.getTag().split(","),"|"));
        BeanUtils.copyProperties(questionDTO,question);
        List<Question> byTag = questionExtMapper.findByTag(question);
        return byTag;
    }
}
