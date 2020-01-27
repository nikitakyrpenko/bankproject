package domains.abstracts;

import domains.Operation;
import domains.User;
import domains.enumeration.OperationType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static domains.utility.CollectionUtility.nullSafeListInitialize;

public abstract class Account implements TransferProcessable {

    //TODO метод который возвращает ежимесячные начисления

    private final Integer id;
    private final User holder;
    private final Date expirationDate;
    private List<Operation> operations;
    private BigDecimal balance;

    public Account(Integer id, User holder, List<Operation> operations, Date expirationDate, BigDecimal balance) {
        this.id = id;
        this.holder = holder;
        this.operations = nullSafeListInitialize(operations);
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

    public BigDecimal getBalance() {
        return balance;
    }

    private void updateOperations(Operation operation) {
        List<Operation> updatedOperations = new ArrayList<>(this.operations);
        updatedOperations.add(operation);
        this.operations = nullSafeListInitialize(updatedOperations);
    }

    public abstract void chargingMonthlyInterestOnAccount(Operation operation);

    public abstract BigDecimal calculateInterestPerMonth();


    @Override
    public void processTransfer(Operation operation) {
        if (operation.getOperationType() != OperationType.CREDIT_ARRIVAL) {
            final BigDecimal transfer = operation.getTransfer();
            if (this.equals(operation.getSenderOfTransaction())) {
                this.balance = this.balance.subtract(transfer);
            }
            if (this.equals(operation.getReceiverOfTransaction())) {
                this.balance = this.balance.add(transfer);
            }
        }
        updateOperations(operation);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder=" + holder +
                ", operations=" + operations +
                ", balance=" + balance +
                '}';
    }
}
