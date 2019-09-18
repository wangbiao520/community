package com.majiang.community.controller;

import com.majiang.community.dto.InformDTO;
import com.majiang.community.model.User;
import com.majiang.community.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InformController {

    @Autowired
    private InformService informService;

    @GetMapping("/inform/{id}")
    public String inform(HttpServletRequest request,
                         @PathVariable(name = "id") Long id){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:";
        }
        InformDTO informDTO = informService.readInform(id);
        return "redirect:/question/" + informDTO.getInformId();

    }

}
