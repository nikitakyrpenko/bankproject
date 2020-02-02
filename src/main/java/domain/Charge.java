package domain;

import domain.enums.ChargeType;
import java.util.Objects;

public class Charge {

    private final Integer    id;
    private final Double     charge;
    private final ChargeType chargeType;
    private Account    account;

    public Charge(Integer id, Double charge, ChargeType chargeType){
        this.id = id;
        this.charge = charge;
        this.chargeType = chargeType;
    }

    public Integer getId() {
        return id;
    }

    public Double getCharge() {
        return charge;
    }

    public Account getAccount() {
        return account;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", charge=" + charge +
                ", chargeType=" + chargeType +
                ", account=" + account +
                '}'+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charge charge1 = (Charge) o;
        return Objects.equals(id, charge1.id) &&
                Objects.equals(charge, charge1.charge) &&
                chargeType == charge1.chargeType &&
                Objects.equals(account, charge1.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, charge, chargeType, account);
    }
}

