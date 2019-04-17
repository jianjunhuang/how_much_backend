package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.user.User;
import com.jianjun.entity.request.LoginRequest;
import com.jianjun.service.ITokenService;
import com.jianjun.service.IUserService;
import com.jianjun.utils.FormatChecker;
import com.jianjun.utils.ParamsChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService mUserService;

    @Resource
    private ITokenService mTokenService;

    @Resource
    private UUIDGenerator uuidGenerator;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<User> login(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<LoginRequest> body) {
        BaseResponse<User> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        LoginRequest loginRequest = body.getData();
        if (!FormatChecker.email(loginRequest.getEmail())) {
            result.setCode(Code.PARAMTES_ERR);
            result.setMsg("email format is err");
            return result;
        }

        User user = mUserService.requestUserByEmail(loginRequest.getEmail());

        if (user != null) {
            user.setUpdateDate(new Date().getTime());
            result.setData(user);
            mUserService.updateUser(user);
            user.setToken(mTokenService.updateToken(user.getEmail()));
            return result;
        }
        user = new User();
        user.setEmail(loginRequest.getEmail());
        Date date = new Date();
        user.setCreateDate(date.getTime());
        user.setUpdateDate(date.getTime());
        result.setData(user);
        mUserService.addUser(user);
        user.setToken(mTokenService.updateToken(user.getEmail()));
        return result;
    }


}
