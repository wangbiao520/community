package com.majiang.community.controller;

import com.majiang.community.dto.CommentDTO;
import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.enums.CommentTypeEnum;
import com.majiang.community.model.Question;
import com.majiang.community.service.CommentService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){

        QuestionDTO questionVO = questionService.findById(id);
        List<CommentDTO> commentCreateDTOList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(questionVO,questionDTO);
        List<Question> questionList = questionService.findList(questionDTO);

        questionService.incView(id);
        model.addAttribute("question",questionVO);
        model.addAttribute("comments",commentCreateDTOList);
        model.addAttribute("tags",questionList);

        return "question";
    }

}
