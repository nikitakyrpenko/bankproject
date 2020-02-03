package dao;

import dao.impl.ChargeCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.Charge;
import domain.enums.ChargeType;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class ChargeDaoTest {

    private static ConnectorDB connection;
    private static ChargeCrudDaoImpl chargeCrudDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init(){
        connection = new ConnectorDB("h2connection");
        chargeCrudDao = new ChargeCrudDaoImpl(connection);
    }

    @Test
    public void chargeDaoFindAllShouldReturn10IfTableContains10Records(){
        assertEquals( 10, chargeCrudDao.findAll().size());
    }

    @Test
    public void chargeDaoFindByIdShouldReturnCorrectRecordIfRecordWithIdExistInTable(){
        assertNotNull(chargeCrudDao.findById(1).get());
    }

    @Test
    public void chargeDaoFindByIdShouldThrowNoSuchElementExceptionIfRecordWithSuchIdIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        chargeCrudDao.findById(1000).get();
    }

    @Test
    public void chargeDaoFindChargesByAccountIdShouldReturnCorrectRecordIfRecordWithIdExistInTable(){
        assertEquals(1,chargeCrudDao.findAllChargesByAccountId(3).size());
    }

    @Test
    public void chargeDaoFindChargesByAccountIdThrowNoSuchElementExceptionIfRecordIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        assertTrue(chargeCrudDao.findAllChargesByAccountId(100).isEmpty());
    }

    @AfterClass
    public static void destroy() throws SQLException {
        connection.getConnection().close();
    }

}
