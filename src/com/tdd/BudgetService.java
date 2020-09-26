package com.tdd;

import java.time.LocalDate;
import java.util.Date;

public class BudgetService {
    private Date startDay;
    private Date endDay;

    public BudgetService(){


    }

    public double query(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate))
            return 0;


        return -1;
    }
}
