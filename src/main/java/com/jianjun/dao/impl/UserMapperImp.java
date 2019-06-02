package com.jianjun.dao.impl;

import com.jianjun.dao.IUserMapper;
import com.jianjun.entity.user.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public String getEmail(String token) {
        Map map = new HashMap();
        map.put("token", token);
        Email email = sqlSessionTemplate.selectOne("getEmail", map);
        return email.getEmail();
    }

    @Override
    public Budget requestBudget(String email) {
        Map map = new HashMap();
        map.put("email", email);
        return sqlSessionTemplate.selectOne("requestBudget", map);
    }

    @Override
    public int addBudget(String email, BigDecimal budget) {
        Map map = new HashMap();
        map.put("email", email);
        map.put("budget", budget);
        return sqlSessionTemplate.insert("addBudget", map);
    }

    @Override
    public int updateBudget(String email, BigDecimal budget) {
        Map map = new HashMap();
        map.put("email", email);
        map.put("budget", budget);
        return sqlSessionTemplate.update("updateBudget", map);
    }

    @Override
    public String getCode(String email) {
        LoginCode code = sqlSessionTemplate.selectOne("getCode", email);
        return code != null ? code.getCode() : "";
    }

    @Override
    public int updateCode(String email, String code) {
        Map map = new HashMap();
        map.put("email", email);
        map.put("code", code);
        return sqlSessionTemplate.update("updateCode", map);
    }

    @Override
    public int insertCode(String email, String code) {
        Map map = new HashMap();
        map.put("email", email);
        map.put("code", code);
        return sqlSessionTemplate.selectOne("insertCode", map);
    }

    @Override
    public int addToken(Token token) {
        return sqlSessionTemplate.insert("addToken", token);
    }
}
