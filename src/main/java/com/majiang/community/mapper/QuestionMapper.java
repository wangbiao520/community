package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question")
    List<Question> findAll();
    @Select("select * from question limit #{firstPage},#{size}")
    List<Question> findPage(@Param(value = "firstPage") Integer firstPage, @Param(value = "size")Integer size);
    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator = #{id}")
    Integer countByUserId(@Param(value = "id") Long id);

    @Select("select * from question where creator = #{id} limit #{firstPage},#{size}")
    List<Question> findPageByUserId(@Param(value = "id")Long id, @Param(value = "firstPage") Integer firstPage, @Param(value = "size")Integer size);
}
