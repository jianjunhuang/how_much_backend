package com.jianjun.entity.bill;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AddBillRequest {

    private String walletId;
    private BigDecimal money;
    private String memo;
    private String position;
    private List<String> typeIds;
    private String title;
    private Long date;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public long getData() {
        if (date == null) {
            return new Date().getTime();
        }
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
