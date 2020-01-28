package domain;

import domain.CreditAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreditAccountTest {

    public CreditAccount creditAccount;

    @Parameter(0)
    public Double creditLimit;

    @Parameter(1)
    public Double creditRate ;

    @Parameter(2)
    public Double expectedCharge;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(
                new Object[][]{
                        {50000.0, 0.2, 1858.17},
                        {30000.0, 0.12, 996.43},
                        {5000.0, 0.12, 166.07}
                }
        );
    }

    @Before
    public void init(){
         creditAccount = new CreditAccount.CreditAccountBuilder()
                 .withCreditLimit(creditLimit)
                 .withCreditRate(creditRate)
                 .build();
    }

    @Test
    public void getChargeShouldReturnValueByCreditLimitAndCreditRate(){
        assertEquals(expectedCharge,creditAccount.getCharge(),0.1);
    }



    @Test(expected = IllegalArgumentException.class)
    public void getChargeThrowIllegalArgumentExceptionIfCreditRateLessThanZero(){
        CreditAccount creditAccount = new CreditAccount.CreditAccountBuilder()
                .withCreditLimit(0.0)
                .withCreditRate(0.0)
                .build();
        creditAccount.getCharge();
    }

}
