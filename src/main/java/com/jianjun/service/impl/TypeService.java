package com.jianjun.service.impl;

import com.jianjun.dao.ITypeMapper;
import com.jianjun.entity.bill.BillType;
import com.jianjun.entity.type.Type;
import com.jianjun.service.ITypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeService implements ITypeService {

    @Resource
    private ITypeMapper mTypeMapper;

    @Override
    public List<BillType> queryTypes(String billId) {
        return mTypeMapper.queryTypes(billId);
    }

    @Override
    public boolean addTypes(Type type) {
        return mTypeMapper.addTypes(type) >= 0;
    }

    @Override
    public boolean bindType(String billId, String typeId) {
        return mTypeMapper.bindType(billId, typeId) >= 0;
    }

    @Override
    public boolean deleteType(String typeId) {
        return mTypeMapper.deleteType(typeId) >= 0;
    }

    @Override
    public boolean unbindType(String billId, String typeId) {
        return mTypeMapper.unbindType(billId, typeId) >= 0;
    }
}
