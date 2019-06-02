package com.jianjun.dao.impl;


import com.jianjun.dao.IBillMapper;
import com.jianjun.entity.bill.BillResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillMapperImp implements IBillMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<BillResponse> getBills(String email) {
        return sqlSessionTemplate.selectList("getBills", email);
    }

    @Override
    public List<BillResponse> getRegularBills(String email) {
        return sqlSessionTemplate.selectList("getRegularBills", email);
    }

    @Override
    public List<BillResponse> getBillsByDate(String email, long start, long end) {
        Map map = new HashMap();
        map.put("email", email);
        map.put("start", start);
        map.put("end", end);
        return sqlSessionTemplate.selectList("getBillsByDate", map);
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

    @Override
    public List<BillResponse> queryBillByType(String typeId) {
        return sqlSessionTemplate.selectList("queryBillByType", typeId);
    }

    @Override
    public List<BillResponse> queryBillByWallet(String walletId) {
        return sqlSessionTemplate.selectList("queryBillByWallet", walletId);
    }
}
