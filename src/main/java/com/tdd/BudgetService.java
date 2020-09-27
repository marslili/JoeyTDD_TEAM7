package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

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
        int lengthOfEndDateMonth = YearMonth.of(endDate.getYear(), endDate.getMonth().getValue()).lengthOfMonth();
        long totalMonths = getMonthBetween(startDate, endDate, lengthOfEndDateMonth);
        LocalDate tmpDate;
        double amount = 0.0;

        for(Budget vo: budgetList){
            amount = 0.0;
            for (int i = 0;i<=totalMonths;i++) {
                tmpDate = startDate.plusMonths(i);
                String tmpYearMon = getYearMonth(tmpDate);
                if (vo.getYearMonth().equals(tmpYearMon)) {
                    amount = vo.getAmount();
                    break;
                }
            }
            totalBudget +=  amount;
        }


        if (startDate.getDayOfMonth() > 1) {
            String tmpYearMon = getYearMonth(startDate);
            for (Budget vo : budgetList) {
                if (vo.getYearMonth().equals(tmpYearMon)) {
                    amount = vo.getAmount();
                    break;
                }
            }
            double budget = amount;
            double outOfRangeDays = DAYS.between(LocalDate.of(startDate.getYear(), startDate.getMonth().getValue(), 1), startDate);
            int lengthOfMonth = YearMonth.of(startDate.getYear(), startDate.getMonth().getValue()).lengthOfMonth();
            totalBudget -= (budget / lengthOfMonth) * outOfRangeDays;
        }

        if (endDate.getDayOfMonth() < lengthOfEndDateMonth) {
            String tmpYearMon = getYearMonth(endDate);
            for (Budget vo : budgetList) {
                if (vo.getYearMonth().equals(tmpYearMon)) {
                    amount = vo.getAmount();
                    break;
                }
            }
            double budget = amount;
            double outOfRangeDays = DAYS.between(endDate, LocalDate.of(endDate.getYear(), endDate.getMonth(), lengthOfEndDateMonth));
            totalBudget -= (budget / lengthOfEndDateMonth) * outOfRangeDays;
        }
        return totalBudget;
    }

    private long getMonthBetween(LocalDate startDate, LocalDate endDate, int lengthOfEndDateMonth) {
        return MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(lengthOfEndDateMonth));
    }

    private String getYearMonth(LocalDate startDate) {
        String month;
        if (startDate.getMonth().getValue() < 10) {
            month = "0" + startDate.getMonth().getValue();
        } else {
            month = String.valueOf(startDate.getMonth().getValue());
        }
        String yearMonth = startDate.getYear() + "" + month;
        return yearMonth;
    }

    private double getMonthBudget(String yearMonth) {
        double amount = 0.0;
        ArrayList<Budget> budgetList = budgetRepo.getAll();
        for (Budget vo : budgetList) {
            if (vo.getYearMonth().equals(yearMonth)) {
                amount = vo.getAmount();
            }
        }
        return amount;
    }
}
