package com.jianjun.service.impl;

import com.jianjun.dao.IUserMapper;
import com.jianjun.entity.user.Budget;
import com.jianjun.entity.user.Token;
import com.jianjun.entity.user.User;
import com.jianjun.service.IUserService;
import com.jianjun.utils.EmptyChecker;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper mUserMapper;

    @Override
    public User requestUserByEmail(String email) {
        if (!EmptyChecker.isEmpty(email)) {
            return mUserMapper.requestUserByEmail(email);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return mUserMapper.addUser(user) >= 0;
    }

    @Override
    public boolean updateUser(User user) {
        return mUserMapper.updateUser(user) >= 0;
    }

    @Override
    public Budget requestBudget(String email) {
        return mUserMapper.requestBudget(email);
    }

    @Override
    public boolean addBudget(String email, BigDecimal budget) {
        return mUserMapper.addBudget(email, budget) >= 0;
    }

    @Override
    public boolean updateBudget(String email, BigDecimal budget) {
        return mUserMapper.updateBudget(email, budget) >= 0;
    }

    @Override
    public String getCode(String email) {
        return mUserMapper.getCode(email);
    }

    @Override
    public void updateCode(String email, String code) {
        String oldCode = getCode(email);
        if (TextUtils.isEmpty(oldCode)) {
            mUserMapper.insertCode(email, code);
        } else {
            mUserMapper.updateCode(email, code);
        }
    }
}
