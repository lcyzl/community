package com.cyz.community.community.mapper;

import com.cyz.community.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);
}
