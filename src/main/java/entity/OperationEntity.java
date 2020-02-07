package entity;

import java.util.Date;
import java.util.Objects;

public class OperationEntity {
    private final Integer         id;
    private final String          purposeOfTransaction;
    private AccountEntity receiverOfTransaction;
    private AccountEntity senderOfTransaction;
    private final Date            dateOfTransaction;
    private final Double          transfer;

    public OperationEntity(Builder builder) {
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

    public AccountEntity getSenderOfTransaction() {
        return senderOfTransaction;
    }

    public String getPurposeOfTransaction() {
        return purposeOfTransaction;
    }

    public AccountEntity getReceiverOfTransaction() {
        return receiverOfTransaction;
    }

    public void setReceiverOfTransaction(AccountEntity receiverOfTransaction) {
        this.receiverOfTransaction = receiverOfTransaction;
    }

    public void setSenderOfTransaction(AccountEntity senderOfTransaction) {
        this.senderOfTransaction = senderOfTransaction;
    }

    public static Builder builder(){ return new Builder(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationEntity operationEntity = (OperationEntity) o;
        return Objects.equals(id, operationEntity.id) &&
                Objects.equals(purposeOfTransaction, operationEntity.purposeOfTransaction) &&
                Objects.equals(receiverOfTransaction, operationEntity.receiverOfTransaction) &&
                Objects.equals(senderOfTransaction, operationEntity.senderOfTransaction) &&
                Objects.equals(dateOfTransaction, operationEntity.dateOfTransaction) &&
                Objects.equals(transfer, operationEntity.transfer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purposeOfTransaction, receiverOfTransaction, senderOfTransaction, dateOfTransaction, transfer);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", purposeOfTransaction='" + purposeOfTransaction + '\'' +
                ", receiverOfTransaction=" + receiverOfTransaction +
                ", senderOfTransaction=" + senderOfTransaction +
                ", dateOfTransaction=" + dateOfTransaction +
                ", transfer=" + transfer +
                '}'+"\n";
    }

    public static class Builder{
         private Integer         id;
         private String          purposeOfTransaction;
         private AccountEntity receiverOfTransaction;
         private AccountEntity senderOfTransaction;
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
         public Builder withReceiver(AccountEntity receiver){
             this.receiverOfTransaction = receiver;
             return this;
         }
         public Builder withSender(AccountEntity sender){
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

         public OperationEntity build(){
             return new OperationEntity(this);
         }
    }
}
