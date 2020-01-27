import domains.DepositAccount;
import domains.abstracts.Account;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepositAccountTest {

    public static Account account = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(1000)
            .withDepositRate(0.13)
            .build();

    public static Account secondAccount = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(557.7)
            .withDepositRate(0.09)
            .build();

    public static Account thirdAccount = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(1000)
            .withDepositRate(0.09)
            .build();


    @Test
    public void chargePercentsShouldReturn1330IfDepositAmount1000AndRate13Percents(){
        double actual = account.calculateInterestPerMonth().doubleValue();
        assertEquals(130.00, actual,0.01);
    }

    @Test
    public void chargePercentsShouldReturn607and89IfDepositAmount557and7AndRate9Percents(){
        double actual = secondAccount.calculateInterestPerMonth().doubleValue();
        assertEquals(50.19, actual,0.01);
    }

    @Test
    public void chargePercentsShouldReturn90IfDepositAmount0(){
        double actual = thirdAccount.calculateInterestPerMonth().doubleValue();
        assertEquals(90.00, actual,0.01);
    }
}
