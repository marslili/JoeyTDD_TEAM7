package com.tdd;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class BudgetService implements IBudgetService{



    public BudgetService(){

    }

    public double query(LocalDate startDate, LocalDate endDate) {

        HashMap<String,Integer> map=getAll();

        if(startDate.isAfter(endDate)){
            return 0;
        }



        long diffMonth = MONTHS.between(startDate,endDate)+1;

        YearMonth yearMonth=YearMonth.of(startDate.getYear(),startDate.getMonth().getValue());
        double totalBudget = 0.0;
        for (int i = 0;i<diffMonth;i++) {

            totalBudget=totalBudget+getMonthBudget(yearMonth,startDate,endDate);
            yearMonth=yearMonth.plusMonths(1);

        }
        return totalBudget;
    }

    private double getMonthBudget(YearMonth yearMonth,LocalDate startDate,LocalDate endDate){

        double amount=0d;
        HashMap<String,Integer> map=getAll();
        String key=yearMonth.getYear()+String.format("%02d", yearMonth.getMonth().getValue());

        if(map.containsKey(key)){
            amount= map.get(key);
            System.out.println("amount="+amount);
        }
        YearMonth startDateMonth = YearMonth.from(startDate);
        YearMonth endDateMonth = YearMonth.from(endDate);
        Double ratio=0d;

        if(startDateMonth.getMonth().getValue()==yearMonth.getMonth().getValue()&&startDateMonth.getYear()==yearMonth.getYear()){
            ratio = (startDate.lengthOfMonth()-startDate.getDayOfMonth()+1)*1.0/(startDate.lengthOfMonth()*1.0);
            if(startDateMonth.equals(endDateMonth)){
               Long diffdays= DAYS.between(startDate,endDate);
                ratio=(diffdays+1)*1.0/startDate.lengthOfMonth();
            }
        }else if(yearMonth.isAfter(startDateMonth)&&yearMonth.isBefore(endDateMonth)){
            ratio=1d;
        }else if(endDateMonth.getMonth().getValue()==yearMonth.getMonth().getValue()&&endDateMonth.getYear()==yearMonth.getYear()){
            ratio = endDate.getDayOfMonth()*1.0/endDate.lengthOfMonth();
        }
        return amount*ratio;
    }

    @Override
    public HashMap<String,Integer> getAll(){
        HashMap<String,Integer> map=new HashMap<>();
        map.put("202010",3000);
        map.put("202009",3000);
        map.put("202008",3100);
        map.put("202007",3100);
        map.put("202006",3000);
        map.put("202005",3100);
        return  map;
    }

}
