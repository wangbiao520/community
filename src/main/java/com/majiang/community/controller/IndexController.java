package com.majiang.community.controller;

import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.service.InformService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private InformService informService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "2") Integer size,
                        Model model){


        //User user = (User)request.getSession().getAttribute("user");
        PageQuestionDTO pageQuestionDTO = questionService.findList(page,size);
        model.addAttribute("page",pageQuestionDTO);

        return "index";
    }

}
