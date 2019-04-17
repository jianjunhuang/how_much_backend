package com.jianjun.service.impl;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.dao.IUserMapper;
import com.jianjun.dao.impl.UserMapperImp;
import com.jianjun.entity.user.Token;
import com.jianjun.service.ITokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenServiceImpl implements ITokenService {

    @Resource
    private IUserMapper mUserMapper;

    @Resource
    private UUIDGenerator uuidGenerator;

    @Override
    public Token updateToken(String email) {
        Token token = mUserMapper.getToken(email);
        if (token == null) {
            token = new Token();
            Date date = new Date();
            token.setUpdateDate(date.getTime());
            token.setCreateDate(date.getTime());
            token.setToken(uuidGenerator.generateUUID());
            token.setEmail(email);
            mUserMapper.addToken(token);
            return token;
        }
        token.setToken(uuidGenerator.generateUUID());
        Date date = new Date();
        token.setCreateDate(date.getTime());
        token.setUpdateDate(date.getTime());
        mUserMapper.updateToken(token);
        return token;
    }

    @Override
    public boolean isValid(String email, Token token) {
        Token t = mUserMapper.getToken(email);
        if (t == null) {
            return false;
        }
        if (!t.getToken().equals(token.getToken())) {
            return false;
        }
        if ((t.getUpdateDate() - t.getCreateDate()) > (3 * 30 * 24 * 60 * 60 * 1000)) {
            return false;
        }
        t.setUpdateDate(new Date().getTime());
        mUserMapper.updateToken(t);
        return true;
    }
}
