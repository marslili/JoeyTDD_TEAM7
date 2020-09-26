package com.tdd;

import java.time.LocalDate;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {



    public BudgetService(){

    }

    public double query(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)){
            return 0;
        }
        Long days=DAYS.between(startDate,endDate);

        return -1;
    }

}
