package dao;


import dao.impl.OperationCrudDaoImpl;
import dao.util.ConnectorDB;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class OperationEntityDaoTest {

    private static ConnectorDB connection;
    private static OperationCrudDaoImpl operationCrudDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init(){
        connection = new ConnectorDB("h2connection");
        operationCrudDao = new OperationCrudDaoImpl(connection);
    }

    @Test
    public void operationCrudDaoFindAllShouldReturn20IfTableContains20Records(){
        assertEquals(20, operationCrudDao.findAll().size());
    }

    @Test
    public void operationCrudDaoFindByIdShouldReturnCorrectRecordIfRecordIsPresent(){
        assertNotNull(operationCrudDao.findById(3).get());
    }

    @Test
    public void operationCrudDaoFindByIdShouldThrowNoSuchElementExceptionIfRecordIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        operationCrudDao.findById(10000).get();
    }

    @Test
    public void operationCrudDaoFindByIdShouldThrowIllegalArgumentExceptionIfPassedParameterIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        operationCrudDao.findById(null);

    }

    @Test
    public void operationCrudDaoFindAllOperationByAccountIdShouldReturnCorrectRecordIfIdIsPresent(){
        assertEquals(5,operationCrudDao.findAllOperationByAccount(3).size());
    }

    @Test
    public void operationCrudDaoFindAllOperationByAccountIdShouldThrowNoSuchExceptionIfRecordIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        operationCrudDao.findAllOperationByAccount(10000);
    }

    @Test
    public void operationCrudDaoFindAllShouldThrowIllegalArgumentExceptionIfPassedParameterIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        operationCrudDao.findAllOperationByAccount(null);
    }
}
