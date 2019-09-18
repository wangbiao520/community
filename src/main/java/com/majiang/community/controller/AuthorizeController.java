package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GitHubUser;
import com.majiang.community.model.User;
import com.majiang.community.provider.GitHubProvider;
import com.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String setClientSecret;
    @Value("${github.redirect.uri}")
    private String setRedirectUuri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response
                           ){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(setClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(setRedirectUuri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);

        if(gitHubUser != null){
            //登录成功 存入session
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            response.addCookie(new Cookie("token",token));

            userService.createOrUpdate(user);
            return "redirect:";
        }else{
            return "redirect:";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                           HttpServletResponse response){

        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:";
    }

}
