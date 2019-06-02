package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.user.AddBudgetRequest;
import com.jianjun.entity.user.Budget;
import com.jianjun.entity.user.User;
import com.jianjun.entity.user.LoginRequest;
import com.jianjun.service.ITokenService;
import com.jianjun.service.IUserService;
import com.jianjun.utils.FormatChecker;
import com.jianjun.utils.ParamsChecker;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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

        String code = mUserService.getCode(loginRequest.getEmail());
        if (!loginRequest.getCode().equals(code)) {
            result.setCode(Code.PARAMTES_ERR);
            result.setMsg("code is err");
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

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/requestBudget", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Budget> requestBudget(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse<Budget> result = ParamsChecker.isNull(body);
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        Budget budget = mUserService.requestBudget(email);
        if (budget == null) {
            budget = new Budget();
            budget.setBudget(BigDecimal.valueOf(1500));
        }
        result.setData(budget);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/updateBudget", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Budget> addBudget(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<AddBudgetRequest> body) {
        BaseResponse<Budget> result = ParamsChecker.isNull(body);
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        Budget budget = mUserService.requestBudget(email);
        if (budget == null) {
            budget = new Budget();
            budget.setBudget(body.getData().getBudget());
            mUserService.addBudget(email, budget.getBudget());
        } else {
            budget.setBudget(body.getData().getBudget());
            mUserService.updateBudget(email, budget.getBudget());
        }
        result.setData(budget);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse sendCode(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        String email = body.getData();
        if (!FormatChecker.email(email)) {
            result.setCode(Code.PARAMTES_ERR);
            result.setMsg("email format is err");
            return result;
        }
        String code = String.valueOf(new Date().getTime());
        OkHttpClient client = new OkHttpClient();
        mUserService.updateCode(email, code);

        Request okRequest = new Request.Builder()
                .url("https://sendcloud.sohu.com/apiv2/mail/send?apiUser=jianjunhuang_test_AmJ8eL&apiKey=LdEllPU55NexUgxw&from=test%40sendcloud.org&to=" + email + "&subject=Login code&plain=" + code)
                .build();

        try {
            client.newCall(okRequest).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setData(email);
        return result;
    }
}
