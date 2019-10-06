package com.cyz.community.community.controller;

import com.cyz.community.community.dto.CommentCreateDTO;
import com.cyz.community.community.dto.CommentDTO;
import com.cyz.community.community.dto.ResultDTO;
import com.cyz.community.community.enums.CommentTypeEnum;
import com.cyz.community.community.exception.CustomizeErrorCode;
import com.cyz.community.community.model.Comment;
import com.cyz.community.community.model.User;
import com.cyz.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postAop(@RequestBody CommentCreateDTO commentDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentDTO == null || StringUtils.isBlank(commentDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMTENT_IS_EMPTY);

        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(34L);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
