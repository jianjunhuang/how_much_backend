package com.jianjun.service;

import com.jianjun.entity.bill.BillResponse;

import java.util.List;

public interface IBillService {
    List<BillResponse> getBills(String email);

    List<BillResponse> getRegularBills(String email);

    List<BillResponse> getBillsByDate(String email, long start, long end);

    boolean addBill(BillResponse billResponse);

    boolean updateBill(BillResponse billResponse);

    boolean deleteBill(String billId);

    List<BillResponse> getBillByType(String typeId);

    List<BillResponse> getBillByWallet(String walletId);

}
