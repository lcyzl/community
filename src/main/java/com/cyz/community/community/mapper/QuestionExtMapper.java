package com.cyz.community.community.mapper;

import com.cyz.community.community.model.Question;

public interface QuestionExtMapper {
    int incCommentCount(Question record);
}
