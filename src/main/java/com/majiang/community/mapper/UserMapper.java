package com.majiang.community.mapper;


import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(NAME,TOKEN,ACCOUNT_ID,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) VALUES(#{name},#{token},#{accountId},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User selectByToken(@Param("token") String token);

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User selectById(@Param("id") Long id);

}
