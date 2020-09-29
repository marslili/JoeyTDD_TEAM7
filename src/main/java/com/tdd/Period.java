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

    public int lengthOfMonth() {
        return lastBudgetDate.lengthOfMonth();
    }

    long daysBetween(Period period) {
        long daysBetween = 0;
        LocalDate tempStartDate = getFirstBudgetDate().isBefore(period.getFirstBudgetDate()) ? period.getFirstBudgetDate() : getFirstBudgetDate();
        LocalDate tempEndDate = getLastBudgetDate().isBefore(period.getLastBudgetDate()) ? getLastBudgetDate() : period.getLastBudgetDate();

        if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
            daysBetween = DAYS.between(tempStartDate, tempEndDate) + 1;
        }
        return daysBetween;
    }

}
