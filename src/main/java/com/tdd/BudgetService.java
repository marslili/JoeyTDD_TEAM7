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

        Period targetPeriod = new Period(startDate, endDate);

        return budgetRepo.getAll().stream().mapToDouble(budget -> new Period(budget.firstDate(), budget.lastDate()).overlappingBudgetAmount(budget, targetPeriod)).sum();
    }
}
