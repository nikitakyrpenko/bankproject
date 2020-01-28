package domain;

import java.util.Date;
import java.util.Objects;

public class Operation {
    private final Integer         id;
    private final String          purposeOfTransaction;
    private final Account         receiverOfTransaction;
    private final Account         senderOfTransaction;
    private final Date            dateOfTransaction;
    private final Double          transfer;

    public Operation(Builder builder) {
        this.id                      = builder.id;
        this.transfer                = builder.transfer;
        this.dateOfTransaction       = builder.dateOfTransaction;
        this.senderOfTransaction     = builder.senderOfTransaction;
        this.purposeOfTransaction    = builder.purposeOfTransaction;
        this.receiverOfTransaction   = builder.receiverOfTransaction;
    }

    public Integer getId() {
        return id;
    }

    public Double getTransfer() {
        return transfer;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public Account getSenderOfTransaction() {
        return senderOfTransaction;
    }

    public String getPurposeOfTransaction() {
        return purposeOfTransaction;
    }

    public Account getReceiverOfTransaction() {
        return receiverOfTransaction;
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
         private Integer         id;
         private String          purposeOfTransaction;
         private Account         receiverOfTransaction;
         private Account         senderOfTransaction;
         private Date            dateOfTransaction;
         private Double          transfer;

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
         public Builder withTransfer(Double transfer){
             this.transfer = transfer;
             return this;
         }

         public Operation build(){
             return new Operation(this);
         }
    }


}
