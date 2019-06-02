package com.jianjun.dao;

import com.jianjun.entity.bill.BillResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBillMapper {

    List<BillResponse> getBills(@Param("email") String email);

    List<BillResponse> getRegularBills(@Param("email") String email);

    List<BillResponse> getBillsByDate(@Param("email") String email, @Param("start") long start, @Param("end") long end);

    int addBill(BillResponse billResponse);

    int updateBill(BillResponse billResponse);

    int deleteBill(@Param("bill_id") String billId);

    List<BillResponse> queryBillByType(@Param("typeId") String typeId);

    List<BillResponse> queryBillByWallet(@Param("walletId") String walletId);
}
