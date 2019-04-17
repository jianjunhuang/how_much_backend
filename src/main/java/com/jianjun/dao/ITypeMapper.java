package com.jianjun.dao;

import com.jianjun.entity.bill.BillType;
import com.jianjun.entity.type.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITypeMapper {

    List<BillType> queryTypes(@Param("bill_id") String billId);

    int addTypes(Type type);

    int bindType(@Param("bill_id") String billId, @Param("type_id") String typeId);

    int deleteType(@Param("type_id") String typeId);

    int unbindType(@Param("bill_id") String billId, @Param("type_id") String typeId);

}
