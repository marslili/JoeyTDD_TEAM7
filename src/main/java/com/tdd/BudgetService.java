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
        long totalMonths = getMonthBetween(startDate, endDate);
        LocalDate tmpDate;
        double amount = 0.0;
        LocalDate startMonthFirstDate = LocalDate.of(startDate.getYear(), startDate.getMonth().getValue(), 1);
        LocalDate endMonthLastDate = LocalDate.of(endDate.getYear(), endDate.getMonth(), lengthOfEndDateMonth);

        for(Budget vo: budgetList){
            for (int i = 0;i<=totalMonths;i++) {
                tmpDate = startDate.plusMonths(i);
                String tmpYearMon = getYearMonth(tmpDate);
                if (vo.getYearMonth().equals(tmpYearMon)) {
                    amount = vo.getAmount();
                    break;
                }
            }
            totalBudget +=  amount;


            String tmpYearMon = getYearMonth(startDate);
            if (tmpYearMon.equals(vo.getYearMonth()) && startDate.getDayOfMonth() > 1) {
                double outOfRangeDays = DAYS.between(startMonthFirstDate, startDate);
                int lengthOfMonth = YearMonth.of(startDate.getYear(), startDate.getMonth().getValue()).lengthOfMonth();
                totalBudget -= (amount / lengthOfMonth) * outOfRangeDays;
            }


            tmpYearMon = getYearMonth(endDate);
            if (tmpYearMon.equals(vo.getYearMonth()) && endDate.getDayOfMonth() < lengthOfEndDateMonth) {
                double outOfRangeDays = DAYS.between(endDate, endMonthLastDate);
                int lengthOfMonth = lengthOfEndDateMonth;
                totalBudget -= (amount / lengthOfMonth) * outOfRangeDays;
            }
        }

        return totalBudget;
    }

    private long getMonthBetween(LocalDate startDate, LocalDate endDate) {
        return MONTHS.between(startDate.withDayOfMonth(1), endDate.plusMonths(1));
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
