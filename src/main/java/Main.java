import domains.CreditAccount;
import domains.enumeration.AccountType;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        CreditAccount creditAccount = CreditAccount.builder()
                .withLimit(BigDecimal.valueOf(50000))
                .withRate(BigDecimal.valueOf(0.2))
                .withAccountType(AccountType.CREDIT)
                .build();
        creditAccount.chargePercents();
    }
}
