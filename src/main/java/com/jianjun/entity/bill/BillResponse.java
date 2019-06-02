package com.jianjun.entity.bill;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账单
 */
public class BillResponse {

    private String billId;
    private String email;
    private long createDate;
    private long updateDate;
    private BigDecimal money;
    private String memo;
    private String position;
    private List<BillType> billTypeList;
    private String WalletName;
    private String WalletId;
    private String title;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWalletName() {
        return WalletName;
    }

    public void setWalletName(String walletName) {
        WalletName = walletName;
    }

    public String getWalletId() {
        return WalletId;
    }

    public void setWalletId(String walletId) {
        WalletId = walletId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<BillType> getBillTypeList() {
        return billTypeList;
    }

    public void setBillTypeList(List<BillType> billTypeList) {
        this.billTypeList = billTypeList;
    }
}
