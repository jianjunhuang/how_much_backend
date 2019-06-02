package com.jianjun.service;

import com.jianjun.entity.user.Budget;
import com.jianjun.entity.user.User;

import java.math.BigDecimal;

public interface IUserService {

    User requestUserByEmail(String email);

    boolean addUser(User user);

    boolean updateUser(User user);

    Budget requestBudget(String email);

    boolean addBudget(String email, BigDecimal budget);

    boolean updateBudget(String email, BigDecimal budget);

    String getCode(String email);

    void updateCode(String email, String code);
}
