package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class BudgetService{


    IBudgetRepo budgetRepo;
    public BudgetService(IBudgetRepo budgetRepo){
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate startDate, LocalDate endDate) {
        ArrayList<Budget> budgetList = budgetRepo.getAll();

        if(startDate.isAfter(endDate)){
            return 0;
        }

        long diffDays = DAYS.between(startDate,endDate)+1;
        int totalDays = startDate.lengthOfMonth();
        /*
           is same month check
         */
        if(startDate.getMonth() == endDate.getMonth()){
            String yearMonth = getYearMonth(startDate);
            /*
                整月
             */
            if( totalDays==diffDays ){
                for(Budget vo:budgetList){
                    if(vo.getYearMonth().equals(yearMonth)){
                        return vo.getAmount();
                    }
                }

            }//不足月
            else{
                for(Budget vo:budgetList){
                    if(vo.getYearMonth().equals(yearMonth)){
                        return vo.getAmount()*diffDays/totalDays;
                    }
                }
            }
        }else{
            int lengthOfEndDateMonth = YearMonth.of(endDate.getYear(), endDate.getMonth().getValue()).lengthOfMonth();
            long diffMonth = MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(lengthOfEndDateMonth));
            System.out.println("diffMonth:"+diffMonth);
            LocalDate tmpDate ;
            double totalBudget = 0.0;
            String startYearMonth = getYearMonth(startDate);
            double amount1 = 0.0;
            for(Budget vo1 : budgetList){
                if(vo1.getYearMonth().equals(startYearMonth)){
                    amount1 = vo1.getAmount();
                }
            }
            totalBudget = totalBudget + amount1;
            System.out.println("startYearMonth:"+startYearMonth);

            for (int i = 0;i<diffMonth;i++) {
                tmpDate = startDate.plusMonths(i+1);
                String tmpYearMon = getYearMonth(tmpDate);
                System.out.println("tmpYearMon:"+tmpYearMon);
                double amount = 0.0;
                for(Budget vo: budgetList){
                    if(vo.getYearMonth().equals(tmpYearMon)){
                        amount  = vo.getAmount();
                    }
                }
                totalBudget = totalBudget + amount;
            }
            if(startDate.getDayOfMonth()>1){
                String tmpYearMon = getYearMonth(startDate);
                double amount = 0.0;
                for(Budget vo: budgetList){
                    if(vo.getYearMonth().equals(tmpYearMon)){
                        amount  = vo.getAmount();
                    }
                }
                double budget = amount;
                double outOfRangeDays = DAYS.between(LocalDate.of(startDate.getYear(),startDate.getMonth().getValue(), 1), startDate);
                int lengthOfMonth = YearMonth.of(startDate.getYear(), startDate.getMonth().getValue()).lengthOfMonth();
                totalBudget -= (budget/lengthOfMonth)*outOfRangeDays;
            }


            if(endDate.getDayOfMonth()< lengthOfEndDateMonth){
                String tmpYearMon = getYearMonth(endDate);
                double amount = 0.0;
                for(Budget vo: budgetList){
                    if(vo.getYearMonth().equals(tmpYearMon)){
                        amount  = vo.getAmount();
                    }
                }
                double budget = amount;
                double outOfRangeDays = DAYS.between(endDate, LocalDate.of(endDate.getYear(),endDate.getMonth(), lengthOfEndDateMonth));
                totalBudget -= (budget/lengthOfEndDateMonth)*outOfRangeDays;
            }
            return totalBudget;
        }


        return -1;
    }

    private String getYearMonth(LocalDate startDate) {
        String month;
        if(startDate.getMonth().getValue()<10){
            month = "0"+ startDate.getMonth().getValue();
        }else{
            month =String.valueOf(startDate.getMonth().getValue());
        }
        String yearMonth= startDate.getYear()+""+month;
        return yearMonth;
    }

    private double getMonthBudget(String yearMonth){
        double amount = 0.0;
        ArrayList<Budget> budgetList=budgetRepo.getAll();
        for(Budget vo:budgetList){
            if(vo.getYearMonth().equals(yearMonth)){
                amount  = vo.getAmount();
            }
        }
        return amount;
    }
}
