package domains;

import domains.abstracts.Account;
import domains.enumeration.OperationType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Operation {
    private Integer         id;
    private String          purposeOfTransaction;
    private Account         receiverOfTransaction;
    private Account         senderOfTransaction;
    private Date            dateOfTransaction;
    private OperationType   operationType;
    private BigDecimal      transfer;

    public Operation(Builder builder) {
        this.id                      = builder.id;
        this.purposeOfTransaction    = builder.purposeOfTransaction;
        this.receiverOfTransaction   = builder.receiverOfTransaction;
        this.senderOfTransaction     = builder.senderOfTransaction;
        this.dateOfTransaction       = builder.dateOfTransaction;
        this.operationType           = builder.operationType;
        this.transfer                = builder.transfer;
    }

    public Integer getId() {
        return id;
    }

    public String getPurposeOfTransaction() {
        return purposeOfTransaction;
    }

    public Account getReceiverOfTransaction() {
        return receiverOfTransaction;
    }

    public Account getSenderOfTransaction() {
        return senderOfTransaction;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public BigDecimal getTransfer() {
        return transfer;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public static Builder builder(){ return new Builder(); }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) &&
                Objects.equals(purposeOfTransaction, operation.purposeOfTransaction) &&
                Objects.equals(receiverOfTransaction, operation.receiverOfTransaction) &&
                Objects.equals(senderOfTransaction, operation.senderOfTransaction) &&
                Objects.equals(dateOfTransaction, operation.dateOfTransaction) &&
                Objects.equals(transfer, operation.transfer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purposeOfTransaction, receiverOfTransaction, senderOfTransaction, dateOfTransaction, transfer);
    }

    public static class Builder{
         Integer         id;
         String          purposeOfTransaction;
         Account         receiverOfTransaction;
         Account         senderOfTransaction;
         Date            dateOfTransaction;
         OperationType   operationType;
         BigDecimal      transfer;


         public Builder withId(Integer id){
             this.id = id;
             return this;
         }
         public Builder withPurpose(String purpose){
             this.purposeOfTransaction = purpose;
             return this;
         }
         public Builder withReceiver(Account receiver){
             this.receiverOfTransaction = receiver;
             return this;
         }
         public Builder withSender(Account sender){
             this.senderOfTransaction = sender;
             return this;
         }
         public Builder withDate(Date date){
             this.dateOfTransaction = date;
             return this;
         }
         public Builder withTransfer(BigDecimal transfer){
             this.transfer = transfer;
             return this;
         }
         public Builder withOperationType(OperationType operationType){
             this.operationType = operationType;
             return this;
         }

         public Operation build(){
             return new Operation(this);
         }
    }


}
