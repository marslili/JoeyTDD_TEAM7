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

        for(Budget budget: budgetList){
            LocalDate firstBudgetDate = budget.firstDate();
            LocalDate lastBudgetDate = budget.lastDate();

            LocalDate tempStartDate = firstBudgetDate.isBefore(startDate)?startDate:firstBudgetDate;
            LocalDate tempEndDate = lastBudgetDate.isBefore(endDate)?lastBudgetDate:endDate;

            if(tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)){
                long daysBetween = DAYS.between(tempStartDate, tempEndDate)+1;
                amount = budget.getAmount();
                totalBudget += amount/lastBudgetDate.lengthOfMonth() *daysBetween;
            }
        }

        return totalBudget;
    }

}
