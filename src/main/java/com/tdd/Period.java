package com.tdd;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate firstBudgetDate;
    private final LocalDate lastBudgetDate;

    public Period(LocalDate firstBudgetDate, LocalDate lastBudgetDate) {
        this.firstBudgetDate = firstBudgetDate;
        this.lastBudgetDate = lastBudgetDate;
    }

    public int lengthOfMonth() {
        return lastBudgetDate.lengthOfMonth();
    }

    long daysBetween(Period period) {
        long daysBetween = 0;
        LocalDate tempStartDate = firstBudgetDate.isBefore(period.firstBudgetDate) ? period.firstBudgetDate : firstBudgetDate;
        LocalDate tempEndDate = lastBudgetDate.isBefore(period.lastBudgetDate) ? lastBudgetDate : period.lastBudgetDate;

        if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
            daysBetween = DAYS.between(tempStartDate, tempEndDate) + 1;
        }
        return daysBetween;
    }

}
