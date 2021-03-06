package domain;

import domain.abstraction.OperationProcessable;
import entity.AccountEntity;
import entity.OperationEntity;
import entity.UserEntity;
import entity.enums.AccountType;

import java.util.Date;
import java.util.List;

public abstract class Account implements OperationProcessable {
    private final Integer id;
    private final Date expirationDate;
    private User holder;
    private Double          balance;

    public Account(AccountBuilder builder) {
        this.id             = builder.id;
        this.holder         = builder.holder;
        this.balance        = builder.balance;
        this.expirationDate = builder.expirationDate;
    }

    @Override
    public void processTransfer(OperationEntity operationEntity) {
        final Double  transfer = operationEntity.getTransfer();
        final AccountEntity sender   = operationEntity.getSenderOfTransaction();
        final AccountEntity receiver = operationEntity.getReceiverOfTransaction();
        if (this.equals(sender)){
            this.balance = this.balance - transfer;
        }
        if (this.equals(receiver)){
            this.balance = this.balance + transfer;
        }
    }

    public Integer getId() {
        return id;
    }

    public User getHolder() {
        return holder;
    }

    public Double getBalance() {
        return balance;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public abstract Double getCharge();

    public abstract AccountType getAccountType();

    public void setHolder(User holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder=" + holder +
                ", expirationDate=" + expirationDate +
                ", balance=" + balance +
                '}';
    }

    public abstract static  class AccountBuilder<SELF extends AccountBuilder<SELF>>{
        private Integer         id;
        private User holder;
        private Double          balance;
        private List<Operation> operationEntities;
        private Date            expirationDate;

        public AccountBuilder(){}

        public SELF withId(Integer id){
            this.id = id;
            return self();
        }
        public SELF withHolder(User holder){
            this.holder = holder;
            return self();
        }
        public SELF withDate(Date expirationDate){
            this.expirationDate = expirationDate;
            return self();
        }
        public SELF withBalance(Double balance){
            this.balance = balance;
            return self();
        }
        public SELF withOperations(List<Operation> operationEntities){
            this.operationEntities = operationEntities;
            return self();
        }
        public abstract Account build();

        @SuppressWarnings("unchecked")
        protected SELF self(){
            return (SELF)this;
        }
    }
}
