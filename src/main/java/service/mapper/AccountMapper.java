package service.mapper;

import domain.Account;
import domain.CreditAccount;
import domain.DepositAccount;
import entity.AccountEntity;
import entity.CreditAccountEntity;
import entity.DepositAccountEntity;
import entity.enums.AccountType;

public class AccountMapper {


    public Account mapAccountEntityToAccount(AccountEntity accountEntity) {
        Account account;
        if (accountEntity.getAccountType() == AccountType.CREDIT) {
            CreditAccountEntity creditAccountEntity = (CreditAccountEntity) accountEntity;
            account = new CreditAccount.CreditAccountBuilder()
                    .withId(creditAccountEntity.getId())
                    .withDate(creditAccountEntity.getExpirationDate())
                    .withBalance(creditAccountEntity.getBalance())
                    .withCreditLimit(creditAccountEntity.getLimit())
                    .withCreditRate(creditAccountEntity.getRate())
                    .withCreditLiability(creditAccountEntity.getLiability())
                    .withAccountType(AccountType.CREDIT)
                    .build();
        } else {
            DepositAccountEntity depositAccountEntity = (DepositAccountEntity) accountEntity;
            account = new DepositAccount.DepositAccountBuilder()
                    .withId(depositAccountEntity.getId())
                    .withDate(depositAccountEntity.getExpirationDate())
                    .withBalance(depositAccountEntity.getBalance())
                    .withDepositAmount(depositAccountEntity.getDepositAmount())
                    .withDepositRate(depositAccountEntity.getDepositRate())
                    .withAccountType(AccountType.DEPOSIT)
                    .build();
        }
        return account;
    }
}
