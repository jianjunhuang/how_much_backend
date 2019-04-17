package com.jianjun.service;

import com.jianjun.entity.user.User;

public interface IUserService {

    User requestUserByEmail(String email);

    boolean addUser(User user);

    boolean updateUser(User user);

}
