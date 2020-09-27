package com.tdd;

import java.time.YearMonth;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;

public class Budget {

    private YearMonth currentMonth;
    private Integer amount;
    private Double ratio;


    public Double getTotalAmount() {
        return ratio*amount;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

//    public void setPeriod(Period period) {
//        this.period = period;
//    }

//    public double getTotalAmount() {
//        Double ratio = 0d;
//        //頭
//        if (currentMonth.equals(period.getStartYearMonth())) {
//            ratio = (period.getStartDate().lengthOfMonth() - period.getStartDate().getDayOfMonth() + 1) * 1.0 / (period.getStartDate().lengthOfMonth() * 1.0);
//            if (period.getStartYearMonth().equals(period.getEndYearMonth())) {//起訖同月
//                Long diffDays = DAYS.between(period.getStartDate(), period.getEndDate());
//                ratio = (diffDays + 1) * 1.0 / period.getStartDate().lengthOfMonth();
//            }
//        } else if (currentMonth.isAfter(period.getStartYearMonth()) && currentMonth.isBefore(period.getEndYearMonth())) {//中間
//            ratio = 1d;
//        } else if (currentMonth.equals(period.getEndYearMonth()) ) {//尾巴
//            ratio = period.getEndDate().getDayOfMonth() * 1.0 / period.getEndDate().lengthOfMonth();
//        }
//
//        return ratio*amount;
//    }

    public void setCurrentMonth(YearMonth yearMonth) {
        this.currentMonth = yearMonth;
    }

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
