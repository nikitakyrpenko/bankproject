import domains.CreditAccount;
import domains.enumeration.AccountType;
import org.junit.Test;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CreditAccountTest {

    private static CreditAccount creditAccount = CreditAccount.builder()
            .withLimit(BigDecimal.valueOf(50000))
            .withRate(BigDecimal.valueOf(0.2))
            .withAccountType(AccountType.CREDIT)
            .build();
    private static CreditAccount secondCreditAccount = CreditAccount.builder()
            .withLimit(BigDecimal.valueOf(30000))
            .withRate(BigDecimal.valueOf(0.12))
            .withAccountType(AccountType.CREDIT)
            .build();
    private static CreditAccount thirdCreditAccount = CreditAccount.builder()
            .withLimit(BigDecimal.valueOf(5000))
            .withRate(BigDecimal.valueOf(0.12))
            .withAccountType(AccountType.CREDIT)
            .build();

    @Test
    public void chargePercentsShouldReturn1859and2IfCreditLimit50000AndCreditRate20Percents(){
        creditAccount.chargePercents();
        assertEquals(1858.17, creditAccount.getLiabilities().doubleValue(),0.1);
    }

    @Test
    public void chargePercentsShouldReturn996and43IfCreditLimit30000AndCreditRate12Percents(){
        secondCreditAccount.chargePercents();
        assertEquals(996.43, secondCreditAccount.getLiabilities().doubleValue(),0.1);
    }
    @Test
    public void chargePercentsShouldReturn166and07IfCreditLimit5000AndCreditRate12Percents(){
        thirdCreditAccount.chargePercents();
        assertEquals(166.07, thirdCreditAccount.getLiabilities().doubleValue(),0.1);
    }
}
