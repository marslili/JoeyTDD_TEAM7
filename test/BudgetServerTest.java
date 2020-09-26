import com.tdd.BudgetService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class BudgetServerTest {

    BudgetService service;

    @Test
    public void test_illegalStartDay(){

        service = new BudgetService();
        LocalDate startDate;
        startDate = LocalDate.of(2020,7,1);

        LocalDate  endDate;
        endDate = LocalDate.of(2020,6,1);


        double resultamount = service.query(startDate,endDate);
        Assert.assertThat(0.0,is(resultamount));


    }

}
