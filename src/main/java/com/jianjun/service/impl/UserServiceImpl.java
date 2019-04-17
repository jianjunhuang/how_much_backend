package com.jianjun.service.impl;

import com.jianjun.dao.IUserMapper;
import com.jianjun.entity.user.Token;
import com.jianjun.entity.user.User;
import com.jianjun.service.IUserService;
import com.jianjun.utils.EmptyChecker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
