package com.tdd;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {


    IBudgetRepo budgetRepo;

    public BudgetService(IBudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate startDate, LocalDate endDate) {
        ArrayList<Budget> budgetList = budgetRepo.getAll();

        if (startDate.isAfter(endDate)) {
            return 0;
        }

        double totalBudget = 0.0;
        double amount;
        long daysBetween;
        LocalDate firstBudgetDate;
        LocalDate lastBudgetDate;

        for(Budget budget: budgetList){
            amount = budget.getAmount();

            firstBudgetDate = budget.firstDate();
            lastBudgetDate = budget.lastDate();

            daysBetween = daysBetween(startDate, endDate, firstBudgetDate, lastBudgetDate);

            totalBudget += amount/lastBudgetDate.lengthOfMonth() *daysBetween;
        }

        return totalBudget;
    }

    private long daysBetween(LocalDate startDate, LocalDate endDate, LocalDate firstBudgetDate, LocalDate lastBudgetDate) {
        long daysBetween = 0;
        LocalDate tempStartDate = firstBudgetDate.isBefore(startDate)? startDate : firstBudgetDate;
        LocalDate tempEndDate = lastBudgetDate.isBefore(endDate)? lastBudgetDate : endDate;

        if(tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)){
            daysBetween = DAYS.between(tempStartDate, tempEndDate)+1;
        }
        return daysBetween;
    }

}
