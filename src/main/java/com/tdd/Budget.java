package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private final double amount;
    private final String yearMonth;

    public Budget(String yearMonth, double amount) {
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    double overlappingBudgetAmount(Period targetPeriod) {
        Period period = new Period(firstDate(), lastDate());
        return (amount / period.lengthOfMonth()) * period.daysBetween(targetPeriod);
    }

    private LocalDate firstDate() {
        return LocalDate.parse(yearMonth + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private LocalDate lastDate() {
        return YearMonth.from(firstDate()).atEndOfMonth();
    }


}
