package com.jianjun.service.impl;

import com.jianjun.dao.IBillMapper;
import com.jianjun.entity.bill.BillResponse;
import com.jianjun.service.IBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {

    @Resource
    private IBillMapper mBillMapper;

    @Override
    public List<BillResponse> getBills(String email) {
        return mBillMapper.getBills(email);
    }

    @Override
    public boolean addBill(BillResponse billResponse) {
        return mBillMapper.addBill(billResponse) >= 0;
    }

    @Override
    public boolean updateBill(BillResponse billResponse) {
        return mBillMapper.updateBill(billResponse) >= 0;
    }

    @Override
    public boolean deleteBill(String billId) {
        return mBillMapper.deleteBill(billId) >= 0;
    }
}
