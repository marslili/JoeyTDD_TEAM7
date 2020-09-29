package com.tdd;

import java.time.LocalDate;

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
}
