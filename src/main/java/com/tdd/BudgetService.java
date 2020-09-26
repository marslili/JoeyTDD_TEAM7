package com.tdd;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class BudgetService implements IBudgetService{



    public BudgetService(){

    }

    public double query(LocalDate startDate, LocalDate endDate) {
        ArrayList<Budget> budgetList=getAll();

        if(startDate.isAfter(endDate)){
            return 0;
        }

        Long diffDays=DAYS.between(startDate,endDate)+1;
        int totalDays = startDate.lengthOfMonth();
        /*
           is same month check
         */
        if(startDate.getMonth().getValue() == endDate.getMonth().getValue()){
            String yearMonth=startDate.getYear()+"-"+startDate.getMonth().getValue();
            /*
                整月
             */
            if(totalDays==diffDays ){

                for(Budget vo:budgetList){
                    if(vo.yearMonth.equals(yearMonth)){
                        return vo.amount;
                    }
                }

            }//不足月
            else{
                for(Budget vo:budgetList){
                    if(vo.yearMonth.equals(yearMonth)){
                        return vo.amount*diffDays/totalDays;
                    }
                }
            }
        }else{
            long diffMonth = MONTHS.between(startDate,endDate);
            System.out.println("diffMonth:"+diffMonth);
            LocalDate tmpDate ;
            double totalBudget = 0.0;
            String startYearMonth=startDate.getYear()+"-"+startDate.getMonth().getValue();
            totalBudget = totalBudget + getMonthBudget(startYearMonth);
            System.out.println("startYearMonth:"+startYearMonth);
            for (int i = 0;i<diffMonth;i++) {
                tmpDate = startDate.plusMonths(i+1);
                String tmpYearMon = tmpDate.getYear()+"-"+tmpDate.getMonth().getValue();
                System.out.println("tmpYearMon:"+tmpYearMon);
                totalBudget = totalBudget + getMonthBudget(tmpYearMon);
            }
            return totalBudget;
        }


        return -1;
    }

    private double getMonthBudget(String yearMonth){
        double amount = 0.0;
        ArrayList<Budget> budgetList=getAll();
        for(Budget vo:budgetList){
            if(vo.yearMonth.equals(yearMonth)){
                amount  = vo.amount;
            }
        }
        return amount;
    }

    @Override
    public ArrayList<Budget> getAll(){
        ArrayList<Budget> list=new ArrayList<Budget>();
        Budget vo1=new Budget();
        Budget vo2=new Budget();
        Budget vo3=new Budget();
        vo1.amount=3100;
        vo1.yearMonth="2020-7";

        vo2.amount=3000;
        vo2.yearMonth="2020-6";

        vo3.amount=3100;
        vo3.yearMonth="2020-5";

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);

        return  list;
    }
}
