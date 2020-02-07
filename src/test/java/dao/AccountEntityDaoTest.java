package dao;

import dao.impl.AccountCrudDaoImpl;
import dao.util.ConnectorDB;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AccountEntityDaoTest {

    private static ConnectorDB connection;
    private static AccountCrudDaoImpl accountCrudDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init(){
        connection = new ConnectorDB("h2connection");
        accountCrudDao = new AccountCrudDaoImpl(connection);
    }


    @Test
    public void accountCrudDaoFindAllShouldReturn10IfTableContains10Records(){
        assertEquals(12, accountCrudDao.findAll().size());
    }

    @Test
    public void accountCrudDaoFindByIdShouldReturnCorrectRecordIfRecordWithIdExistInTable(){
        assertNotNull(accountCrudDao.findById(1).get());
    }

    @Test
    public void accountCrudDaoFindByIdShouldThrowNoSuchElementExceptionIfRecordIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        accountCrudDao.findById(10000).get();
    }

    @Test
    public void accountCrudDaoFindByIdShouldThrowIllegalArgumentExceptionIfPassedParameterIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        accountCrudDao.findById(null).get();
    }

    @Test
    public void accountCrudDaoFindAccountByUserIdShouldReturnCorrectRecordIfRecordWithIdExistInTable(){
        assertEquals(2, accountCrudDao.findAllAccountsByUserId(11).size());
    }

    @Test
    public void accountCrudDaoFindAccountByUserIdShouldThrowNoSuchElementExceptionIfRecordIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        accountCrudDao.findAllAccountsByUserId(10000);
    }

    @Test
    public void accountCrudDaoFindAccountByUserIdShouldThrowIllegalArgumentExceptionIfPassedValueIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        accountCrudDao.findAllAccountsByUserId(null);
    }

    @AfterClass
    public static void destroy() throws SQLException {
        connection.getConnection().close();
    }

}
