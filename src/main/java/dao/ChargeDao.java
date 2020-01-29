package dao;

import domain.Charge;

import java.util.List;

public interface ChargeDao extends CrudPageableDao<Charge> {

    List<Charge> findAllChargesByAccountId(Integer id);

}
