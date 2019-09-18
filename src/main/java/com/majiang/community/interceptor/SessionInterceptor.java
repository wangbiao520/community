package com.majiang.community.interceptor;

import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.model.UserExample;
import com.majiang.community.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InformService informService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for (Cookie cookie: cookies) {
                if("token".equals(cookie.getName())){
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(cookie.getValue());
                    List<User> users = userMapper.selectByExample(userExample);
                    user = users.get(0);
                    if(user !=null){
                        HttpSession session = request.getSession();
                        session.setAttribute("user",user);
                        Long aLong = informService.unreadCount(user.getId());
                        session.setAttribute("unreadCount",aLong);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
