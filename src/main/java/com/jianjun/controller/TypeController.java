package com.jianjun.controller;

import com.jianjun.base.mybatis.UUIDGenerator;
import com.jianjun.entity.BaseRequest;
import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;
import com.jianjun.entity.bill.AddBillRequest;
import com.jianjun.entity.bill.BillType;
import com.jianjun.entity.type.Type;
import com.jianjun.entity.type.TypeRequest;
import com.jianjun.service.ITokenService;
import com.jianjun.service.ITypeService;
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
@RequestMapping("type")
public class TypeController {

    @Resource
    private ITypeService mTypeService;

    @Resource
    private UUIDGenerator mUUIDGenerator;

    @Resource
    private ITokenService mTokenService;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Type> addType(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<TypeRequest> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        //todo check is token valied
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);
        TypeRequest typeRequest = body.getData();
        Type type = new Type();
        type.setEmail(email);
        type.setType(typeRequest.getType());
        Date now = new Date();
        type.setUpdateDate(now.getTime());
        type.setCreateDate(now.getTime());
        type.setTypeId(mUUIDGenerator.generateUUID());

        mTypeService.addTypes(type);

        result.setData(type);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delType(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        mTypeService.deleteType(body.getData());
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/listByBill", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillType>> queryTypeByBill(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest<String> body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        List<BillType> types = mTypeService.queryTypes(body.getData());

        result.setData(types);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<BillType>> queryAllType(HttpServletRequest request, HttpServletResponse response, @RequestBody BaseRequest body) {
        BaseResponse result = ParamsChecker.isNull(body);
        if (result.getCode() != Code.SUCCESS) {
            return result;
        }
        //todo check is token valied
        String token = body.getToken();
        String email = mTokenService.requestEmail(token);

        List<BillType> types = mTypeService.queryAllTypes(email);

        result.setData(types);
        return result;
    }
}
