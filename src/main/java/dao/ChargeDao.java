package dao;

import entity.ChargeEntity;

import java.util.List;

public interface ChargeDao extends CrudPageableDao<ChargeEntity> {

    //TODO FINDALLBYACCOUNTPAGEABLE
    List<ChargeEntity> findAllChargesByAccountId(Integer id);

}
