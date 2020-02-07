package domain;

import entity.AccountEntity;
import entity.OperationEntity;

import java.util.Date;
import java.util.Objects;

public class Operation {
    private final Integer         id;
    private final String          purposeOfTransaction;
    private Account receiverOfTransaction;
    private Account senderOfTransaction;
    private final Date dateOfTransaction;
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

    public void setReceiverOfTransaction(Account receiverOfTransaction) {
        this.receiverOfTransaction = receiverOfTransaction;
    }

    public void setSenderOfTransaction(Account senderOfTransaction) {
        this.senderOfTransaction = senderOfTransaction;
    }

    public static Builder builder(){ return new Builder(); }


    public static class Builder{
        private Integer         id;
        private String          purposeOfTransaction;
        private Account receiverOfTransaction;
        private Account senderOfTransaction;
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
