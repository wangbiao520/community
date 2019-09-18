package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import com.majiang.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int inCommentCount(Question record);
    List<Question> findByTag(Question record);

}