package com.cyz.community.community.controller;

import com.cyz.community.community.dto.CommentDTO;
import com.cyz.community.community.mapper.CommentMapper;
import com.cyz.community.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(34);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        Map<Object,Object>objectObjectMap = new HashMap<>();
        objectObjectMap.put("message","成功");
        return objectObjectMap;
    }
}
