package com.jianjun.service;

import com.jianjun.entity.bill.BillResponse;

import java.util.List;

public interface IBillService {
    List<BillResponse> getBills(String email);

    boolean addBill(BillResponse billResponse);

    boolean updateBill(BillResponse billResponse);

    boolean deleteBill(String billId);
}
