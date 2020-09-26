import com.tdd.Budget;
import com.tdd.BudgetService;
import com.tdd.IBudgetRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;

public class BudgetServerTest {

    BudgetService service;
    @Before
    public void setUp(){
        IBudgetRepo budgetRepo = new FakeBudgeRepo();
        service = new BudgetService(budgetRepo);
    }

    @Test
    public void test_illegalStartDay(){

        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = LocalDate.of(2020,6,1);

        double result = service.query(startDate,endDate);
        Assert.assertThat(0.0, is(result));
    }

    @Test
    public void test_SingleDayQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        double resultAmount = service.query(startDate,startDate);
        Assert.assertThat(100.0, is(resultAmount));
    }

    @Test
    public void test_SingleMonQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = LocalDate.of(2020,7,31);
        double resultAmount = service.query(startDate,endDate);
        Assert.assertThat(3100.0,is(resultAmount));

    }

    @Test
    public void test_IntervalMonQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = LocalDate.of(2020,7,15);
        double resultAmount = service.query(startDate,endDate);
        Assert.assertThat(1500.0,is(resultAmount));

    }

    @Test
    public void test_DiffMonQuery(){
        LocalDate startDate = LocalDate.of(2020,6,1);
        LocalDate endDate = LocalDate.of(2021,8,31);
        double resultAmount = service.query(startDate,endDate);
        Assert.assertThat(6100.0,is(resultAmount));
    }

    @Test
    public void test_durationFromMonQuery(){
        LocalDate startDate = LocalDate.of(2020,6,1);
        LocalDate endDate = LocalDate.of(2021,8,31);
        double resultAmount = service.query(startDate,endDate);
        Assert.assertThat(6100.0,is(resultAmount));
    }


}

class FakeBudgeRepo implements IBudgetRepo{
    @Override
    public ArrayList<Budget> getAll(){
        ArrayList<Budget> list = new ArrayList<>();

        list.add(new Budget("202007", 3100));
        list.add(new Budget("202006", 3000));
        list.add(new Budget("202005", 3100));

        return list;
    }
}