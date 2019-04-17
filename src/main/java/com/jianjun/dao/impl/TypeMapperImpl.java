package com.jianjun.dao.impl;

import com.jianjun.dao.ITypeMapper;
import com.jianjun.entity.bill.BillType;
import com.jianjun.entity.type.Type;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TypeMapperImpl implements ITypeMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<BillType> queryTypes(String billId) {
        return sqlSessionTemplate.selectList("queryTypes", billId);
    }

    @Override
    public int addTypes(Type type) {
        return sqlSessionTemplate.insert("addTypes", type);
    }

    @Override
    public int bindType(String billId, String typeId) {
        Map map = new HashMap();
        map.put("bill_id", billId);
        map.put("type_id", typeId);
        return sqlSessionTemplate.insert("bindType", map);
    }

    @Override
    public int deleteType(String typeId) {
        return sqlSessionTemplate.update("deleteType", typeId);
    }

    @Override
    public int unbindType(String billId, String typeId) {
        Map map = new HashMap();
        map.put("bill_id", billId);
        map.put("type_id", typeId);
        return sqlSessionTemplate.update("unbindType", map);
    }
}
