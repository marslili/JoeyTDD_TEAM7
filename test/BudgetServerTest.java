import com.tdd.Budget;
import com.tdd.BudgetService;
import com.tdd.IBudgetRepo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        assertThat(result, is(0.0));
    }

    @Test
    public void test_SingleDayQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        double result = service.query(startDate,startDate);
        assertThat(result, is(100.0));
    }

    @Test
    public void test_SingleMonQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = LocalDate.of(2020,7,31);
        double result = service.query(startDate,endDate);
        assertThat(result,is(3100.0));

    }

    @Test
    public void test_IntervalMonQuery(){
        LocalDate startDate = LocalDate.of(2020,7,1);
        LocalDate endDate = LocalDate.of(2020,7,15);
        double result = service.query(startDate,endDate);
        assertThat(result,is(1500.0));

    }

    @Test
    public void test_DiffMonQuery(){
        LocalDate startDate = LocalDate.of(2020,6,1);
        LocalDate endDate = LocalDate.of(2021,8,31);
        double  result= service.query(startDate,endDate);
        assertThat(result,is(6100.0));
    }

    @Test
    public void test_durationFromMonQuery(){
        LocalDate startDate = LocalDate.of(2020,5,30);
        LocalDate endDate = LocalDate.of(2020,7,2);
        double result = service.query(startDate,endDate);
        assertThat(result, is(3400.0));
    }
    @Test
    public void test_no_budget(){
        LocalDate startDate = LocalDate.of(2022,5,30);
        LocalDate endDate = LocalDate.of(2022,7,2);
        double  result = service.query(startDate,endDate);
        assertThat(result, is(0.0));
    }
}

class FakeBudgeRepo implements IBudgetRepo{
    @Override
    public ArrayList<Budget> getAll(){
        ArrayList<Budget> list = new ArrayList<>();

        list.add(new Budget("202005", 3100));
        list.add(new Budget("202006", 3000));
        list.add(new Budget("202007", 3100));

        return list;
    }
}