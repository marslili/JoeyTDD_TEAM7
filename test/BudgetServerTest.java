import com.tdd.BudgetService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;

public class BudgetServerTest {

    BudgetService service;

    @Test
    public void test_illegalStartDay(){

        service = new BudgetService();
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate  endDate = LocalDate.of(2020,6,1);

        double resultamount = service.query(startDate,endDate);
        Assert.assertThat(0.0,is(resultamount));
    }

    @Test
    public void test_SingleDayQuery(){
        service = new BudgetService();
        LocalDate startDate = LocalDate.of(2020,7,1);
        double resultAmount = service.query(startDate,startDate);
        Assert.assertThat(100.0,is(resultAmount));
    }


    @Test
    public void test_SingleMonQuery(){
        service = new BudgetService();
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = startDate.plusDays(30);
        double resultAmount = service.query(startDate,endDate);
        Assert.assertThat(3100.0,is(resultAmount));

    }

}

