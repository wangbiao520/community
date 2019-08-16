package com.majiang.community.mapper;


import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(NAME,TOKEN,ACCOUNT_ID,GMT_CREATE,GMT_MODIFIED) VALUES(#{name},#{token},#{accountId},#{gmtCreate},#{gmtModified})")
    void insert(User user);

}
