package com.tdd;

import java.time.YearMonth;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;

public class Budget {

    private YearMonth currentMonth;
    private Integer amount;
    private Double ratio;


    public Double getTotalAmount() {
        return ratio*amount;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCurrentMonth(YearMonth yearMonth) {
        this.currentMonth = yearMonth;
    }


}
