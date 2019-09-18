package com.majiang.community.controller;

import com.majiang.community.dto.InformDTO;
import com.majiang.community.dto.PageQuestionDTO;
import com.majiang.community.model.Inform;
import com.majiang.community.model.User;
import com.majiang.community.service.InformService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private InformService informService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name="action") String action,
                          Model model,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "2") Integer size){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:";
        }

        if("questions".equals(action)){
            PageQuestionDTO pageQuestionDTO = questionService.findList(user.getId(),page,size);
            model.addAttribute("page",pageQuestionDTO);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","我的回复");
            //获取通知
            PageQuestionDTO pageQuestionDTO = informService.list(user.getId(),page,size);
            model.addAttribute("page",pageQuestionDTO);
        }else{
            return "redirect:";
        }

        return "profile";
    }

}
