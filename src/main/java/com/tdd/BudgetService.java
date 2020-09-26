package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

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
        //整月
        if(totalDays==diffDays){
            String yearMonth=startDate.getYear()+"-"+startDate.getMonth().getValue();
            for(Budget vo:budgetList){
                if(vo.yearMonth.equals(yearMonth)){
                    return vo.amount;
                }
            }

        }

        return -1;
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
