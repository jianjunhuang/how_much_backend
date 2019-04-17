package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.wallet.Wallet;
import com.jianjun.service.IWalletService;
import com.jianjun.utils.EmptyChecker;
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
import java.util.List;

@Controller
@RequestMapping("wallet")
public class WalletController {

    @Resource
    private IWalletService mWalletService;

    @Resource
    private UUIDGenerator uuidGenerator;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<Wallet>> queryWallets(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {

        BaseResponse<List<Wallet>> result = ParamsChecker.isNull(body);

        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        //todo check token

        String email = body.getData();

        List<Wallet> list = mWalletService.queryWallets(email);

        result.setData(list);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Wallet> updateWallets(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<Wallet> body) {

        BaseResponse<Wallet> result = ParamsChecker.isNull(body);

        if (result.getCode() != Code.SUCCESS) {
            return result;
        }

        Wallet wallet = body.getData();
        if (!EmptyChecker.isEmpty(wallet.getWalletId())) {
            wallet.setUpdateDate(new Date().getTime());
            result.setData(wallet);
            if (!mWalletService.updateWallet(wallet)) {
                result.setCode(Code.FAILED);
                result.setMsg("update failed");
            }
            return result;
        }
        long time = new Date().getTime();
        wallet.setWalletId(uuidGenerator.generateUUID());
        wallet.setCreateDate(time);
        wallet.setUpdateDate(time);
        result.setData(wallet);
        if (!mWalletService.addWallet(wallet)) {
            result.setCode(Code.FAILED);
            result.setMsg("insert failed");
        }
        return result;
    }
}
