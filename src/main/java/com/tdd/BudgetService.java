package com.tdd;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class BudgetService implements IBudgetService {


    public BudgetService() {

    }

    public double query(LocalDate startDate, LocalDate endDate) {
        HashMap<String, Integer> map=getAll();

        if (startDate.isAfter(endDate)) {
            return 0;
        }

        Period period=new Period(startDate,endDate);
        Long diffMonth=period.getDiffMonth();

        YearMonth currentMonth = YearMonth.of(startDate.getYear(), startDate.getMonth().getValue());
        double totalBudget = 0.0;
        //以查詢時間逐月去滾
        for (int i = 0; i < diffMonth; i++) {

            Double ratio=period.getOverLapRatio(currentMonth);

            Budget vo=new Budget();
            vo.setCurrentMonth(currentMonth);
            vo.setRatio(ratio);
            vo.setAmount(map.get(currentMonth.format(DateTimeFormatter.ofPattern("yyyyMM"))));

            totalBudget = totalBudget + vo.getTotalAmount();
            currentMonth = currentMonth.plusMonths(1);
        }
        return totalBudget;
    }



    @Override
    public HashMap<String, Integer> getAll() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("202010", 3000);
        map.put("202009", 3000);
        map.put("202008", 3100);
        map.put("202007", 3100);
        map.put("202006", 3000);
        map.put("202005", 3100);
        return map;
    }

}
