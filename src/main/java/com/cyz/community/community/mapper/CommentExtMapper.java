package com.cyz.community.community.mapper;

import com.cyz.community.community.model.Comment;
import com.cyz.community.community.model.Question;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);

}
