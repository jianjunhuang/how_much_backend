package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.bill.*;
import com.jianjun.entity.user.Budget;
import com.jianjun.entity.wallet.Wallet;
import com.jianjun.service.*;
import com.jianjun.utils.ParamsChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Resource
    private ITokenService mTokenService;

    @Resource
    private IUserService mUserService;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryBills(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        List<BillResponse> billResponses = mBillService.getBills(email);
        for (BillResponse billResponse : billResponses) {
            billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
            billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());
        }
        result.setData(billResponses);
        return result;
    }


    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/regular/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryRegularBills(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        List<BillResponse> billResponses = mBillService.getRegularBills(email);
        for (BillResponse billResponse : billResponses) {
            billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
            billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());
        }
        result.setData(billResponses);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list/date", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryBillsByDate(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<BillByDateRequest> body) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        BillByDateRequest byDateRequest = body.getData();
        List<BillResponse> billResponses = mBillService.getBillsByDate(email, byDateRequest.getStart(), byDateRequest.getEnd());
        for (BillResponse billResponse : billResponses) {
            billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
            billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());
        }
        result.setData(billResponses);
        return result;
    }


    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list/type", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryBillsByType(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        String typeId = body.getData();

        List<BillResponse> billResponses = mBillService.getBillByType(typeId);
        for (BillResponse billResponse : billResponses) {
            billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
            billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());
        }
        result.setData(billResponses);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list/wallet", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillResponse>> queryBillsByWalletId(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<BillByWalletRequest> body) {
        BaseResponse<List<BillResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        BillByWalletRequest byWalletRequest = body.getData();
        List<BillResponse> billResponses = mBillService.getBillByWallet(byWalletRequest.getWalletId());
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
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);

        AddBillRequest addBillRequest = body.getData();

        BillResponse billResponse = new BillResponse();
        billResponse.setBillId(mUUIDGenerator.generateUUID());
        billResponse.setEmail(email);
        billResponse.setMemo(addBillRequest.getMemo());
        billResponse.setMoney(addBillRequest.getMoney());
        billResponse.setPosition(addBillRequest.getPosition());
        billResponse.setWalletId(addBillRequest.getWalletId());
        billResponse.setCreateDate(addBillRequest.getData());
        billResponse.setUpdateDate(addBillRequest.getData());
        billResponse.setTitle(addBillRequest.getTitle());
        billResponse.setTag(addBillRequest.getTag());
        billResponse.setWalletName(mWalletService.requestWallets(billResponse.getWalletId()).getName());

        Wallet wallet = mWalletService.requestWallets(addBillRequest.getWalletId());
        wallet.setTotal(wallet.getTotal().add(addBillRequest.getMoney()));
        mWalletService.updateWallet(wallet);

        mBillService.addBill(billResponse);

        for (String typeId : addBillRequest.getTypeIds()) {
            mTypeService.bindType(billResponse.getBillId(), typeId);
        }
        billResponse.setBillTypeList(mTypeService.queryTypes(billResponse.getBillId()));
        result.setData(billResponse);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse deleteBill(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        String billId = body.getData();
        mBillService.deleteBill(billId);

        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/remain", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<RemainResponse>> analytics(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse<List<RemainResponse>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        List<RemainResponse> remainList = new ArrayList<>();
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        List<BillType> types = mTypeService.queryAllTypes(email);


        Calendar calendar = Calendar.getInstance();

        //得到月初
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date start = calendar.getTime();

        //得到月末
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = calendar.getTime();
        List<BillResponse> list = mBillService.getBillsByDate(email, start.getTime(), end.getTime());

        BigDecimal budget = new BigDecimal(1500);
        Budget requestBudget = mUserService.requestBudget(email);
        if (request != null) {
            requestBudget.setBudget(budget);
        }
        //get expend
        BigDecimal expend = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            expend = expend.add(list.get(i).getMoney());
        }
        BigDecimal remain = budget.subtract(expend);

        for (int j = 0; j < types.size(); j++) {
            List<BillResponse> billResponses = mBillService.getBillByType(types.get(j).getTypeId());
            BigDecimal expended = new BigDecimal(0);
            for (int i = 0; i < billResponses.size(); i++) {
                expended = expended.add(billResponses.get(i).getMoney());
            }
            if (expended.intValue() != 0) {
                RemainResponse remainResponse = new RemainResponse();
                int times = remain.intValue() / expended.abs().intValue();
                remainResponse.setTimes(times);
                remainResponse.setTypeName(types.get(j).getType());
                remainList.add(remainResponse);
            }
        }
        result.setData(remainList);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/typeExpend", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<TypeExpend>> typeExpend(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse<List<TypeExpend>> result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        List<TypeExpend> typeExpends = new ArrayList<>();
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        List<BillType> types = mTypeService.queryAllTypes(email);

        for (int j = 0; j < types.size(); j++) {
            List<BillResponse> billResponses = mBillService.getBillByType(types.get(j).getTypeId());
            BigDecimal expended = new BigDecimal(0);
            for (int i = 0; i < billResponses.size(); i++) {
                expended = expended.add(billResponses.get(i).getMoney());
            }
            TypeExpend typeExpend = new TypeExpend();
            typeExpend.setExpend(expended);
            typeExpend.setType(types.get(j).getType());
            typeExpend.setTypeId(types.get(j).getTypeId());
            typeExpends.add(typeExpend);
        }
        result.setData(typeExpends);
        return result;
    }
}
