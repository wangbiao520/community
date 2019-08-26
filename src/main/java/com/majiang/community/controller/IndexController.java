package com.majiang.community.controller;

import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "2") Integer size,
                        Model model){

        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for (Cookie cookie: cookies) {
                if("token".equals(cookie.getName())){
                    user = userMapper.selectByToken(cookie.getValue());
                    if(user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PageQuestionDTO pageQuestionDTO = questionService.findList(page,size);
        model.addAttribute("page",pageQuestionDTO);

        return "index";
    }

}
