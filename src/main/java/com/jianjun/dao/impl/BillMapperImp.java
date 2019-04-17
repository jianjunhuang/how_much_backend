package com.jianjun.dao.impl;


import com.jianjun.dao.IBillMapper;
import com.jianjun.entity.bill.BillResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BillMapperImp implements IBillMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<BillResponse> getBills(String email) {
        return sqlSessionTemplate.selectList("getBills", email);
    }

    @Override
    public int addBill(BillResponse billResponse) {
        return sqlSessionTemplate.insert("addBill", billResponse);
    }

    @Override
    public int updateBill(BillResponse billResponse) {
        return sqlSessionTemplate.update("updateBill", billResponse);
    }

    @Override
    public int deleteBill(String billId) {
        return sqlSessionTemplate.update("deleteBill", billId);
    }
}
