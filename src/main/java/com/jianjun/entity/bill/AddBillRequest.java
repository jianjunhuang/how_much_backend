package com.jianjun.entity.bill;

import java.math.BigDecimal;
import java.util.List;

public class AddBillRequest {

    private String email;
    private String walletId;
    private BigDecimal money;
    private String memo;
    private String position;
    private List<String> typeIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
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

    public List<String> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<String> typeIds) {
        this.typeIds = typeIds;
    }
}
