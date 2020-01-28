package domain;

import domain.abstraction.InterestChargeable;
import domain.enums.AccountType;
import domain.enums.ChargeType;
import java.util.List;
import java.util.Objects;

import static utility.CollectionUtility.*;

public class CreditAccount extends Account implements InterestChargeable {

    private static final int CREDIT_PERIOD                = 12;
    private static final int TOTAL_CREDIT_PERIOD_IN_MONTH = 36;

    private final Double limit;
    private final Double rate;
    private final Double charge;

    private Double       liability;
    private List<Charge> charges;

    private CreditAccount(CreditAccountBuilder builder) {
        super(builder);
        this.limit     = builder.limit;
        this.rate      = builder.rate;
        this.liability = builder.liability;
        this.charge    = calculateCreditLiabilityPerMonth();
        this.charges   = nullSafeListInitialize(builder.charges);
    }

    private Double percents() {
        return rate / CREDIT_PERIOD;
    }

    private Double calculateCreditLiabilityPerMonth() {
        return (limit * percents()) / (1 - Math.pow(1 + percents(), -TOTAL_CREDIT_PERIOD_IN_MONTH));
    }

    @Override
    public void processCharge() {
        Charge incomingCharge = new Charge(this.charge, ChargeType.CREDIT_ARRIVAL, this);
        this.liability        = this.liability + this.charge;
        this.charges          = createCopyAndUpdateUnmodifiableList(charges, incomingCharge);
    }

    @Override
    public Double getCharge() {
        return charge;
    }

    public Double getRate() {
        return rate;
    }

    public Double getLimit() {
        return limit;
    }

    public Double getLiability() {
        return liability;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "limit=" + limit +
                ", rate=" + rate +
                ", charge=" + charge +
                ", liability=" + liability +
                ", charges=" + charges +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditAccount that = (CreditAccount) o;
        return Objects.equals(limit, that.limit) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(charge, that.charge) &&
                Objects.equals(liability, that.liability) &&
                Objects.equals(charges, that.charges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, rate, charge, liability, charges);
    }

    public static class CreditAccountBuilder extends AccountBuilder<CreditAccountBuilder> {
        private Double rate;
        private Double limit;
        private Double liability;
        private List<Charge> charges;
        private AccountType accountType;

        public CreditAccountBuilder() {
        }

        public CreditAccountBuilder withCreditLimit(Double limit) {
            super.withBalance(limit);
            this.limit = limit;
            return self();
        }
        public CreditAccountBuilder withCreditRate(Double rate) {
            if (rate <= 0.0){throw new IllegalArgumentException("credit rate should be more than zero");}
            this.rate = rate;
            return self();
        }
        public CreditAccountBuilder withCreditLiability(Double liability){
            this.liability = liability;
            return self();
        }
        public CreditAccountBuilder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return self();
        }
        public CreditAccountBuilder withCreditCharges(List<Charge> charges){
            this.charges = charges;
            return self();
        }
        @Override
        public CreditAccount build() {
            return new CreditAccount(this);
        }

        @Override
        protected CreditAccountBuilder self() {
            return this;
        }
    }

}
