package domain;

import domain.abstraction.InterestChargeable;
import entity.AccountEntity;
import entity.ChargeEntity;
import entity.DepositAccountEntity;
import entity.enums.AccountType;
import entity.enums.ChargeType;

import java.util.Objects;

public class DepositAccount extends Account implements InterestChargeable {

    private final Double          depositRate;
    private final Double          depositAmount;
    private final AccountType accountType;

    private DepositAccount(DepositAccountBuilder builder) {
        super(builder);
        this.depositRate   = builder.depositRate;
        this.depositAmount = builder.depositAmount;
        this.accountType   = builder.accountType;
    }

    public Double getDepositRate() {
        return depositRate;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }



    @Override
    public Charge processCharge() {
        //TODO how to set charge ID?
        Double deposit         = super.getBalance();
        Double value           = deposit * depositRate;
        super.setBalance(deposit + value);
        Charge incomingChargeEntity = new Charge(1, value, ChargeType.DEPOSIT_ARRIVAL,this);
        return incomingChargeEntity;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public Double getCharge(){
        return super.getBalance() * depositRate;
    }



    public static class DepositAccountBuilder extends AccountBuilder<DepositAccount.DepositAccountBuilder> {

        private Double depositRate;
        private Double depositAmount;
        private AccountType accountType;

        public DepositAccount.DepositAccountBuilder withDepositRate(double depositRate){
            if (depositRate <= 0.0){throw new IllegalArgumentException("deposit rate should be more than zero");}
            this.depositRate = depositRate;
            return this;
        }
        public DepositAccount.DepositAccountBuilder withDepositAmount(double depositAmount){
            super.withBalance(depositAmount);
            this.depositAmount = depositAmount;
            return this;
        }

        public DepositAccount.DepositAccountBuilder withAccountType(AccountType accountType){
            this.accountType = accountType;
            return this;
        }

        @Override
        public DepositAccount build() {
            return new DepositAccount(this);
        }
    }
}
