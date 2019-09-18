package com.majiang.community.controller;

import com.majiang.community.cache.TagCache;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.dto.TagDTO;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import com.majiang.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public  String edit(@PathVariable(name = "id") Long id,
                        Model model){

        QuestionDTO questionDTO = questionService.findById(id);
        if(questionDTO != null){
            List<TagDTO> tagDTOS = TagCache.get();
            model.addAttribute("tags",tagDTOS);
            model.addAttribute("title",questionDTO.getTitle());
            model.addAttribute("description",questionDTO.getDescription());
            model.addAttribute("tag",questionDTO.getTag());
            model.addAttribute("id",questionDTO.getId());
        }
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){

        List<TagDTO> tagDTOS = TagCache.get();
        model.addAttribute("tags",tagDTOS);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "id", required = false) Long id,
            Model model,
            HttpServletRequest request
            ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("id",id);
        model.addAttribute("tags",TagCache.get());

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

        String errorTag = TagCache.errorTag(tag);
        if(StringUtils.isNotBlank(errorTag)){
            model.addAttribute("error","此标签不存在："+errorTag);
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setCreator(user.getId());
        //question.setGmtCreate(System.currentTimeMillis());
        //question.setGmtModified(question.getGmtCreate());
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:";

    }

}
