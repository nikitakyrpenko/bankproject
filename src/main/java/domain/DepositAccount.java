package domain;

import domain.abstraction.InterestChargeable;
import domain.enums.AccountType;
import domain.enums.ChargeType;
import java.util.List;
import java.util.Objects;

public class DepositAccount extends Account implements InterestChargeable {

    private final Double          depositRate;
    private final Double          depositAmount;
    private final AccountType     accountType;

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
        Charge incomingCharge  = new Charge(1, value, ChargeType.DEPOSIT_ARRIVAL);
        incomingCharge.setAccount(this);
       return incomingCharge;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public Double getCharge(){
        return super.getBalance() * depositRate;
    }

    @Override
    public String toString() {
        return "DepositAccount{" +
                "depositRate=" + depositRate +
                ", depositAmount=" + depositAmount +
                ", accountType=" + accountType +
                "} " + super.toString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositAccount that = (DepositAccount) o;
        return Objects.equals(depositRate, that.depositRate) &&
                Objects.equals(depositAmount, that.depositAmount) &&
                accountType == that.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositRate, depositAmount, accountType);
    }

    public static class DepositAccountBuilder extends AccountBuilder<DepositAccountBuilder>{

        private Double depositRate;
        private Double depositAmount;
        private AccountType accountType;

        public DepositAccountBuilder withDepositRate(double depositRate){
            if (depositRate <= 0.0){throw new IllegalArgumentException("deposit rate should be more than zero");}
            this.depositRate = depositRate;
            return this;
        }
        public DepositAccountBuilder withDepositAmount(double depositAmount){
            super.withBalance(depositAmount);
            this.depositAmount = depositAmount;
            return this;
        }

        public DepositAccountBuilder withAccountType(AccountType accountType){
            this.accountType = accountType;
            return this;
        }

        @Override
        public DepositAccount build() {
            return new DepositAccount(this);
        }
    }

}
