package com.jianjun.dao;

import com.jianjun.entity.user.Budget;
import com.jianjun.entity.user.Token;
import com.jianjun.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

public interface IUserMapper {

    User requestUserByEmail(@Param("email") String email);

    int addUser(User user);

    int updateUser(User user);

    int addToken(Token token);

    Token getToken(@Param("email") String email);

    int updateToken(Token token);

    String getEmail(@Param("token") String token);

    Budget requestBudget(@Param("email") String email);

    int addBudget(@Param("email") String email, @Param("budget") BigDecimal budget);

    int updateBudget(@Param("email") String email, @Param("budget") BigDecimal budget);

    String getCode(@Param("email") String email);

    int updateCode(@Param("email") String email, @Param("code") String code);

    int insertCode(@Param("email") String email, @Param("code") String code);
}
