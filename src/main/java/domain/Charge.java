package domain;

import entity.AccountEntity;
import entity.enums.ChargeType;

public class Charge {
    private final Integer    id;
    private final Double     charge;
    private final ChargeType chargeType;
    private Account account;

    public Charge(Integer id, Double charge, ChargeType chargeType, Account account){
        this.id = id;
        this.charge = charge;
        this.chargeType = chargeType;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public Double getCharge() {
        return charge;
    }

    public Account getAccountEntity() {
        return account;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

}
