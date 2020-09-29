package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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

    LocalDate firstDate() {
        return LocalDate.parse(getYearMonth() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    LocalDate lastDate() {
        return YearMonth.from(firstDate()).atEndOfMonth();
    }

    double overlappingBudgetAmount(Period targetPeriod) {
        Period period = new Period(firstDate(), lastDate());
        return (getAmount() / period.lengthOfMonth()) * period.daysBetween(targetPeriod);
    }
}
