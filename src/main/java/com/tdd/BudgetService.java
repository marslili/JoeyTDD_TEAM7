package com.tdd;

import java.time.LocalDate;

public class BudgetService {


    IBudgetRepo budgetRepo;

    public BudgetService(IBudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return 0;
        }

        double totalBudget = 0;
        for (Budget budget : budgetRepo.getAll()) {
            Period period = new Period(budget.firstDate(), budget.lastDate());
            Period targetPeriod = new Period(startDate, endDate);
            totalBudget += period.overlappingBudgetAmount(budget, targetPeriod);
        }
        return totalBudget;
    }
}
