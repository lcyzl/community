package com.cyz.community.community.controller;

import com.cyz.community.community.dto.PaginationDTO;
import com.cyz.community.community.service.QuestionService;
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
    @GetMapping("/")
    public String indexAop(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                        @RequestParam(value = "size",defaultValue = "7")Integer size){
        System.out.println("我在indexAop");
        PaginationDTO pagination = questionService.List(pn,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
