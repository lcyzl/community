package com.cyz.community.community.service;

import com.cyz.community.community.dto.PaginationDTO;
import com.cyz.community.community.dto.QuestionDTO;
import com.cyz.community.community.mapper.QuestionMapper;
import com.cyz.community.community.mapper.UserMapper;
import com.cyz.community.community.model.Question;
import com.cyz.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO List(Integer pn, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,pn,size);
        if (pn<1){
            pn = 1;
        }
        if (pn > paginationDTO.getTotalPage()){
            pn = paginationDTO.getTotalPage();
        }
        Integer offset = size * (pn - 1);
        List<Question> questions = questionMapper.List(offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
