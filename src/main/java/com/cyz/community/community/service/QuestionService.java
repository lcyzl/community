package com.cyz.community.community.service;

import com.cyz.community.community.dto.PaginationDTO;
import com.cyz.community.community.dto.QuestionDTO;
import com.cyz.community.community.mapper.QuestionMapper;
import com.cyz.community.community.mapper.UserMapper;
import com.cyz.community.community.model.Question;
import com.cyz.community.community.model.QuestionExample;
import com.cyz.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalCount = questionMapper.countByExample(new QuestionExample());
//                questionMapper.count();

        paginationDTO.setPagination(totalCount,pn,size);
        if (pn < 1){
            pn = 1;
        }
        if (pn > paginationDTO.getTotalPage()){
            pn = paginationDTO.getTotalPage();
        }
        Integer offset = size * (pn - 1);
        if (offset < 1){
            offset = 0;
        }
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
//                questionMapper.List(offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
//                    userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount =questionMapper.countByExample(questionExample);
//                questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if (page<1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        if (offset < 1){
            offset = 0;
        }
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));;
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user =userMapper.selectByPrimaryKey(question.getCreator());
//                    userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user =userMapper.selectByPrimaryKey(question.getCreator());
//                userMapper.findById(question.getCreator());

        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){

            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question,example);
        }
    }
}
