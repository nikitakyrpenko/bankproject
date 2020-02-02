package dao;

import dao.impl.ChargeCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.Charge;
import domain.enums.ChargeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class ChargeDaoTest {

    private ConnectorDB connectorDB;
    private ChargeCrudDaoImpl chargeCrudDao;

    @Before
    public void init(){
        connectorDB = new ConnectorDB("database");
        chargeCrudDao = new ChargeCrudDaoImpl(connectorDB);
    }

    @Test
    public void chargeDaoFindByIdShouldReturnChargeById(){
        Charge expected = new Charge(1, 581.0, ChargeType.CREDIT_ARRIVAL);
        Optional<Charge> actual = chargeCrudDao.findById(1);
        Assert.assertEquals(expected,actual.get());

    }
}
