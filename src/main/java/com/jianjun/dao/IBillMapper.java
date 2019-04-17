package com.jianjun.dao;

import com.jianjun.entity.bill.BillResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBillMapper {

    List<BillResponse> getBills(@Param("bill_id") String email);

    int addBill(BillResponse billResponse);

    int updateBill(BillResponse billResponse);

    int deleteBill(@Param("bill_id") String billId);

}
