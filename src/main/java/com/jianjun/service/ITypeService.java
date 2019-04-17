package com.jianjun.service;

import com.jianjun.entity.bill.BillType;
import com.jianjun.entity.type.Type;

import java.util.List;

public interface ITypeService {
    List<BillType> queryTypes(String billId);

    boolean addTypes(Type type);

    boolean bindType(String billId, String typeId);

    boolean deleteType(String typeId);

    boolean unbindType(String billId, String typeId);

}
