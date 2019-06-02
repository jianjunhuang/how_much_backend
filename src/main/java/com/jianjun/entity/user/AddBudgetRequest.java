package com.jianjun.entity.user;

import java.math.BigDecimal;

public class AddBudgetRequest {

    private BigDecimal budget;

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
