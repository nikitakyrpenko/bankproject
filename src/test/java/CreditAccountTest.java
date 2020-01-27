import domains.CreditAccount;
import domains.enumeration.AccountType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditAccountTest {

    private static CreditAccount creditAccount = CreditAccount.builder()
            .withLimit(50000)
            .withRate(0.2)
            .withAccountType(AccountType.CREDIT)
            .build();
    private static CreditAccount secondCreditAccount = CreditAccount.builder()
            .withLimit(30000)
            .withRate(0.12)
            .withAccountType(AccountType.CREDIT)
            .build();
    private static CreditAccount thirdCreditAccount = CreditAccount.builder()
            .withLimit(5000)
            .withRate(0.12)
            .withAccountType(AccountType.CREDIT)
            .build();

    @Test
    public void chargePercentsShouldReturn1859and2IfCreditLimit50000AndCreditRate20Percents(){
        double actual = creditAccount.calculateInterestPerMonth().doubleValue();
        assertEquals(1858.17, actual,0.1);
    }

    @Test
    public void chargePercentsShouldReturn996and43IfCreditLimit30000AndCreditRate12Percents(){
        double actual = secondCreditAccount.calculateInterestPerMonth().doubleValue();
        assertEquals(996.43, actual,0.1);
    }
    @Test
    public void chargePercentsShouldReturn166and07IfCreditLimit5000AndCreditRate12Percents(){
        double actual = thirdCreditAccount.calculateInterestPerMonth().doubleValue();
        assertEquals(166.07, actual,0.1);
    }
}
