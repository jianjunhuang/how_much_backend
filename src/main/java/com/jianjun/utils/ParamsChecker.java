package com.jianjun.utils;

import com.jianjun.entity.BaseResponse;
import com.jianjun.entity.Code;

public class ParamsChecker {

    public static BaseResponse isNull(Object o) {
        BaseResponse result = new BaseResponse();
        if (o == null) {
            result.setCode(Code.PARAMTES_ERR);
            result.setMsg("params is null");
        }
        return result;
    }

}
