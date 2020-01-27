package domains;

import domains.abstracts.Account;
import domains.enumeration.AccountType;
import domains.enumeration.OperationType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreditAccount extends Account {

    private static final int TOTAL_CREDIT_PERIOD_IN_MONTH = 36;
    private static final int CREDIT_PERIOD = 12;

    private final BigDecimal limit;
    private final BigDecimal rate;
    private final BigDecimal charge;

    private BigDecimal liabilities;

    private CreditAccount(Builder builder){
        super(builder.id,builder.holder,builder.operations,builder.expirationDate,builder.limit);
        this.limit       = builder.limit;
        this.rate        = builder.rate;
        this.liabilities = BigDecimal.ZERO;
        this.charge      = creditChargePerMonth();
    }

    private double percentagePerMonth() {
        return (rate.doubleValue() / CREDIT_PERIOD);
    }

    private BigDecimal creditChargePerMonth() {
        double percents = percentagePerMonth();
        double creditLimit = limit.doubleValue();
        double chargePerMonth = (creditLimit * percents) / (1 - Math.pow(1 + percents, -TOTAL_CREDIT_PERIOD_IN_MONTH));
        return BigDecimal.valueOf(chargePerMonth);
    }


    public BigDecimal getLiabilities() {
        return liabilities;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public void chargingMonthlyInterestOnAccount(Operation operation) {
        if (operation.getOperationType() == OperationType.CREDIT_ARRIVAL) {
            liabilities = liabilities.add(operation.getTransfer()).setScale(2, RoundingMode.CEILING);
        }
        super.processTransfer(operation);
    }

    @Override
    public BigDecimal calculateInterestPerMonth() {
        return charge;
    }



    public static class Builder {
        Integer id;

        User holder;

        List<Operation> operations;

        Date expirationDate;

        BigDecimal limit;

        BigDecimal rate;

        AccountType accountType;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withHolder(User holder) {
            this.holder = holder;
            return this;
        }

        public Builder withOperations(List<Operation> operations) {
            this.operations = operations;
            return this;
        }

        public Builder withExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }
        public Builder withAccountType(AccountType accountType){
            this.accountType = accountType;
            return this;
        }
        public Builder withLimit(double limit){
            this.limit = BigDecimal.valueOf(limit);
            return this;
        }
        public Builder withRate(double rate){
            this.rate = BigDecimal.valueOf(rate);
            return this;
        }

        public CreditAccount build(){
            return new CreditAccount(this);
        }

    }

}
