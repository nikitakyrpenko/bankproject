package domain;

import domain.abstraction.OperationProcessable;
import domain.enums.AccountType;

import java.util.Date;
import java.util.List;

import static utility.CollectionUtility.*;

public abstract class Account implements OperationProcessable {

    private final Integer id;
    private final User    holder;
    private final Date    expirationDate;

    private List<Operation> operations;
    private Double          balance;

    public Account(AccountBuilder builder) {
        this.id             = builder.id;
        this.holder         = builder.holder;
        this.balance        = builder.balance;
        this.operations     = nullSafeListInitialize(builder.operations);
        this.expirationDate = builder.expirationDate;
    }


    @Override
    public void processTransfer(Operation operation) {
        final Double  transfer = operation.getTransfer();
        final Account sender   = operation.getSenderOfTransaction();
        final Account receiver = operation.getReceiverOfTransaction();
        if (this.equals(sender)){
            this.balance = this.balance - transfer;
        }
        if (this.equals(receiver)){
            this.balance = this.balance + transfer;
        }
        this.operations = createCopyAndUpdateUnmodifiableList(operations, operation);
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

    public List<Operation> getOperations() {
        return operations;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public abstract Double getCharge();

    public abstract AccountType getAccountType();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder=" + holder +
                ", expirationDate=" + expirationDate +
                ", operations=" + operations +
                ", balance=" + balance +
                '}';
    }

    public abstract static  class AccountBuilder<SELF extends AccountBuilder<SELF>>{
        private Integer         id;
        private User            holder;
        private Double          balance;
        private List<Operation> operations;
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
        public SELF withOperations(List<Operation> operations){
            this.operations = operations;
            return self();
        }
        public abstract Account build();

        @SuppressWarnings("unchecked")
        protected SELF self(){
            return (SELF)this;
        }
    }
}
