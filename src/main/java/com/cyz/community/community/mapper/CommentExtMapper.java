package com.cyz.community.community.mapper;

import com.cyz.community.community.model.Comment;
import com.cyz.community.community.model.Question;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
