package domains;

import domains.abstracts.Account;
import domains.enumeration.AccountType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class DepositAccount extends Account {

    private final BigDecimal depositRate;
    private final List<Operation> replenishment;
    private AccountType accountType;

    private DepositAccount(Builder builder) {
        super(builder.id, builder.holder, builder.operations, builder.expirationDate, builder.depositAmount);
        this.depositRate = builder.depositRate;
        this.replenishment = builder.replenishment;
        this.accountType = builder.accountType;
    }

    @Override
    public void chargePercents() {
        setBalance(getBalance().add(getBalance().multiply(depositRate)));
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    public List<Operation> getReplenishment() {
        return replenishment;
    }

    public AccountType getAccountType() { return accountType; }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        Integer id;

        User holder;

        List<Operation> operations;

        Date expirationDate;

        BigDecimal depositRate;

        BigDecimal depositAmount;

        List<Operation> replenishment;

        AccountType accountType;

        public Builder withId(Integer id){
            this.id = id;
            return this;
        }
        public Builder withHolder(User holder){
            this.holder = holder;
            return this;
        }
        public Builder withOperations(List<Operation> operations){
            this.operations = operations;
            return this;
        }
        public Builder withExpirationDate(Date expirationDate){
            this.expirationDate = expirationDate;
            return this;
        }
        public Builder withDepositRate(BigDecimal depositRate){
            this.depositRate = depositRate;
            return this;
        }
        public Builder withDepositAmount(BigDecimal depositAmount){
            this.depositAmount = depositAmount;
            return this;
        }
        public Builder withReplenishment(List<Operation> replenishment){
            this.replenishment = replenishment;
            return this;
        }
        public Builder withAccountType(AccountType accountType){
            this.accountType = accountType;
            return this;
        }
        public DepositAccount build(){
            return new DepositAccount(this);
        }
    }
}
