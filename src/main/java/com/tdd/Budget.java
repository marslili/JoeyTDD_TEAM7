package com.tdd;

public class Budget {
    private double amount;
    private String yearMonth;

    public Budget( String yearMonth, double amount) {
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    public double getAmount() {
        return amount;
    }

    public String getYearMonth() {
        return yearMonth;
    }
}
