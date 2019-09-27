package com.dajian.community.mapper;

import com.dajian.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("INSERT INTO user (name,account_id,token,created_time,modified_time) values (#{name}, #{accountId},#{token},#{createdTime},#{modifiedTime})")
    void insert(User user);

    @Select("SELECT * FROM `user` WHERE `token` =#{token}")
    User queryUserByToken(@Param("token") String token);
}
