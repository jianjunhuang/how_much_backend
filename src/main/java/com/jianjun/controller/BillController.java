package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.bill.AddBillRequest;
import com.jianjun.entity.bill.BillResponse;
import com.jianjun.entity.type.Type;
import com.jianjun.service.IBillService;
import com.jianjun.service.ITypeService;
import com.jianjun.service.IWalletService;
import com.jianjun.utils.ParamsChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("bill")
public class BillController {

    @Resource
    private IBillService mBillService;

    @Resource
    private ITypeService mTypeService;

    @Resource
    private IWalletService mWalletService;

    @Resource
    private UUIDGenerator mUUIDGenerator;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryBills(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> emailRequest) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(emailRequest);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String email = emailRequest.getData();
        List<BillResponse> billResponses = mBillService.getBills(email);
        for (BillResponse billResponse : billResponses) {
            billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
            billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());
        }
        result.setData(billResponses);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<BillResponse> addBills(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<AddBillRequest> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        AddBillRequest addBillRequest = body.getData();
        BillResponse billResponse = new BillResponse();
        billResponse.setBillId(mUUIDGenerator.generateUUID());
        billResponse.setEmail(addBillRequest.getEmail());
        billResponse.setMemo(addBillRequest.getMemo());
        billResponse.setMoney(addBillRequest.getMoney());
        billResponse.setPosition(addBillRequest.getPosition());
        billResponse.setWalletId(addBillRequest.getWalletId());
        Date now = new Date();
        billResponse.setCreateDate(now.getTime());
        billResponse.setUpdateDate(now.getTime());
        billResponse.setTitle(addBillRequest.getTitle());
        billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());

        mBillService.addBill(billResponse);

        for (String typeId : addBillRequest.getTypeIds()) {
            mTypeService.bindType(billResponse.getBillId(), typeId);
        }
        billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
        result.setData(billResponse);
        return result;
    }
}
