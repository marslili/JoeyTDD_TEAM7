package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.temporal.ChronoUnit.MONTHS;

public class Period {

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private LocalDate startDate;
    private LocalDate endDate;

    public Long getDiffMonth(){
        return MONTHS.between(YearMonth.from(this.startDate), YearMonth.from(this.endDate)) + 1;
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public YearMonth getStartYearMonth() {
        return YearMonth.from(this.startDate);
    }



    public YearMonth getEndYearMonth() {
        return YearMonth.from(this.endDate);
    }


}
