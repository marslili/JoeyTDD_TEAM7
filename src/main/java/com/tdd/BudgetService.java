package com.tdd;

import java.time.LocalDate;
import java.util.ArrayList;

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

            Period period = new Period(firstBudgetDate, lastBudgetDate);
            daysBetween = period.daysBetween(startDate, endDate);

            totalBudget += amount/ lastBudgetDate.lengthOfMonth() *daysBetween;
        }

        return totalBudget;
    }

}
