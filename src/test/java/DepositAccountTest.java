import domains.DepositAccount;
import domains.abstracts.Account;
import org.junit.Test;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DepositAccountTest {

    public static Account account = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(BigDecimal.valueOf(1000))
            .withDepositRate(BigDecimal.valueOf(0.13))
            .build();

    public static Account secondAccount = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(BigDecimal.valueOf(557.7))
            .withDepositRate(BigDecimal.valueOf(0.09))
            .build();

    public static Account thirdAccount = DepositAccount.builder()
            .withId(1)
            .withHolder(null)
            .withDepositAmount(BigDecimal.valueOf(0))
            .withDepositRate(BigDecimal.valueOf(0.09))
            .build();


    @Test
    public void chargePercentsShouldReturn1330IfDepositAmount1000AndRate13Percents(){
        account.chargePercents();
        assertEquals(1130.00, account.getBalance().doubleValue(),0.01);
    }

    @Test
    public void chargePercentsShouldReturn607and89IfDepositAmount557and7AndRate9Percents(){
        secondAccount.chargePercents();
        assertEquals(607.89, secondAccount.getBalance().doubleValue(),0.01);
    }

    @Test
    public void chargePercentsShouldReturn0IfDepositAmount0(){
        thirdAccount.chargePercents();
        assertEquals(0, thirdAccount.getBalance().doubleValue(),0.01);
    }
}
