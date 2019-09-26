package com.cyz.community.community.controller;

import com.cyz.community.community.dto.PaginationDTO;
import com.cyz.community.community.mapper.UserMapper;
import com.cyz.community.community.model.User;
import com.cyz.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String prifile(HttpServletRequest request,
                          @PathVariable(name = "action")String action,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "1")Integer size,
                          Model model){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.listByUserId(user.getId(),page,size);

        model.addAttribute("pagination",paginationDTO);

        return "profile";
    }
}
