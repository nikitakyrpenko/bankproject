package domains.abstracts;

import domains.Operation;
import domains.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class Account {

    private final Integer id;

    private final User holder;

    private final List<Operation> operations;

    private final Date expirationDate;

    private  BigDecimal balance;

    public Account(Integer id, User holder, List<Operation> operations, Date expirationDate, BigDecimal balance) {
        this.id = id;
        this.holder = holder;
        this.operations = operations;
        this.expirationDate = expirationDate;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public User getHolder() {
        return holder;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public BigDecimal getBalance(){return balance;}

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    public abstract void chargePercents();


}
