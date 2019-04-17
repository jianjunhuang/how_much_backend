package com.jianjun.dao.impl;

import com.jianjun.dao.IUserMapper;
import com.jianjun.entity.user.Token;
import com.jianjun.entity.user.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserMapperImp implements IUserMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public User requestUserByEmail(String email) {
        Map map = new HashMap();
        map.put("email", email);
        return sqlSessionTemplate.selectOne("getUserByEmail", map);
    }

    @Override
    public int addUser(User user) {
        return sqlSessionTemplate.insert("addUser", user);
    }

    @Override
    public int updateUser(User user) {
        return sqlSessionTemplate.update("updateUsr", user);
    }


    @Override
    public Token getToken(String email) {
        return sqlSessionTemplate.selectOne("getToken", email);
    }

    @Override
    public int updateToken(Token token) {

        return sqlSessionTemplate.update("updateToken", token);
    }

    @Override
    public int addToken(Token token) {
        return sqlSessionTemplate.insert("addToken", token);
    }
}
