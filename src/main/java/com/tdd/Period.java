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

    public LocalDate getFirstBudgetDate() {
        return firstBudgetDate;
    }

    public LocalDate getLastBudgetDate() {
        return lastBudgetDate;
    }

    long daysBetween(LocalDate startDate, LocalDate endDate) {
        long daysBetween = 0;
        LocalDate tempStartDate = getFirstBudgetDate().isBefore(startDate)? startDate : getFirstBudgetDate();
        LocalDate tempEndDate = getLastBudgetDate().isBefore(endDate)? getLastBudgetDate() : endDate;

        if(tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)){
            daysBetween = DAYS.between(tempStartDate, tempEndDate)+1;
        }
        return daysBetween;
    }
}
