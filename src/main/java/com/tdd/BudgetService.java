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
        int lengthOfEndDateMonth = getLengthOfDateOfMonth(endDate);
        long totalMonths = getMonthBetween(startDate, endDate);
        LocalDate tmpDate;
        double amount = 0.0;

        LocalDate startMonthFirstDate = getFirstDate(startDate);
        LocalDate endMonthLastDate = getLastDate(endDate, lengthOfEndDateMonth);

        for(Budget vo: budgetList){
            for (int i = 0; i<=totalMonths; i++) {
                tmpDate = startDate.plusMonths(i);
                final String yearMonth = getYearMonth(tmpDate);
                if (vo.getYearMonth().equals(yearMonth)) {
                    amount = vo.getAmount();
                    totalBudget += amount;

                    if (tmpDate.getYear()==startDate.getYear() &&
                            tmpDate.getMonth()==startDate.getMonth() &&
                            startDate.getDayOfMonth() > 1) {
                        double outOfRangeDays = DAYS.between(startMonthFirstDate, tmpDate);
                        int lengthOfMonth = getLengthOfDateOfMonth(tmpDate);
                        totalBudget -= (amount / lengthOfMonth) * outOfRangeDays;
                    }

                    if (tmpDate.getYear() == endDate.getYear() &&
                            tmpDate.getMonth() == endDate.getMonth() &&
                     endDate.getDayOfMonth() < lengthOfEndDateMonth) {
                        double outOfRangeDays = DAYS.between(endDate, endMonthLastDate);
                        int lengthOfMonth = lengthOfEndDateMonth;
                        totalBudget -= (amount / lengthOfMonth) * outOfRangeDays;
                    }
                    break;
                }
            }
        }

        return totalBudget;
    }

    private int getLengthOfDateOfMonth(LocalDate localDate) {
        return YearMonth.of(localDate.getYear(), localDate.getMonth().getValue()).lengthOfMonth();
    }

    private LocalDate getLastDate(LocalDate endDate, int lengthOfEndDateMonth) {
        return LocalDate.of(endDate.getYear(), endDate.getMonth(), lengthOfEndDateMonth);
    }

    private LocalDate getFirstDate(LocalDate startDate) {
        return LocalDate.of(startDate.getYear(), startDate.getMonth().getValue(), 1);
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
}
