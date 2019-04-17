package com.jianjun.dao;

import com.jianjun.entity.user.Token;
import com.jianjun.entity.user.User;
import org.apache.ibatis.annotations.Param;

public interface IUserMapper {

    User requestUserByEmail(@Param("email") String email);

    int addUser(User user);

    int updateUser(User user);

    int addToken(Token token);

    Token getToken(@Param("email") String email);

    int updateToken(Token token);
}
