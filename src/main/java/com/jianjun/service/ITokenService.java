package com.jianjun.service;

import com.jianjun.entity.user.Token;

public interface ITokenService {

    Token updateToken(String email);

    boolean isValid(String email, Token token);

    String requestEmail(String token);
}
