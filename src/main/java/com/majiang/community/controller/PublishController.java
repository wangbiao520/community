package com.majiang.community.controller;

import com.majiang.community.mapper.QuestionMapper;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "tag") String tag,
            Model model,
            HttpServletRequest request
            ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || "".equals(title)){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (description == null || "".equals(description)){
            model.addAttribute("error","内容不能为空！");
            return "publish";
        }
        if (tag == null || "".equals(tag)){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
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

        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        questionMapper.create(question);
        return "redirect:";

    }

}
