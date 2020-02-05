package dao;


import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;
import domain.enums.Role;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;


public class UserDaoTest {
    private static ConnectorDB connection;
    private static UserCrudDaoImpl userCrudDao;

    private static String SCHEMA;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @BeforeClass
    public static void init() throws Exception{
        //SCHEMA = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/schema.sql")));
        connection = new ConnectorDB("h2connection");
        userCrudDao = new UserCrudDaoImpl(connection);

    }

    @Test
    public void userCrudDaoFindAllShouldReturn11IfTableContains11Records(){
        assertEquals(userCrudDao.findAll().size(),12);
    }

    @Test
    public void userCrudDaoFindByIdShouldReturnCorrectRecordIfRecordWithIdExistInTable(){
        Optional actual = userCrudDao.findById(1);
        User expected   = User.builder()
                .withId(1)
                .withName("Freya")
                .withSurname("Rodriguez")
                .withTelephone("715-0987")
                .withEmail("dolor.Donec@etmagnaPraesent.net")
                .withPassword("1")
                .withRole(Role.CLIENT)
                .build();
        assertEquals(expected,actual.get());
    }

    @Test
    public void userCrudDaoFindByIdShouldThrowNoSuchElementExceptionIfUserWithSuchIdIsAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        Optional<User> byId = userCrudDao.findById(101);
        byId.get();
    }

    @Test
    public void userCrudDaoFindByEmailShouldReturnCorrectRecordIfRecordWithSuchEmailPresent(){
        User expected = User.builder()
                .withId(11)
                .withName("Mykyta")
                .withSurname("Kyrpenko")
                .withEmail("nikitakyrpenko@gmail.com")
                .withPassword("12134r56")
                .withTelephone("380508321899")
                .withRole(Role.CLIENT)
                .build();
        Optional<User> actual = userCrudDao.findByEmail("nikitakyrpenko@gmail.com");
        assertEquals(expected, actual.get());
    }

    @Test
    public void userCrudDaoFindByEmailShouldThrowNoSuchElementExceptionIfRecordWithSuchIdAbsent(){
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No value present");
        userCrudDao.findByEmail("123").get();
    }

    @Test
    public void userCrudDaoFindByAccountIdShouldReturnCorrectUserByAccountId(){
        User expected = User.builder()
                .withId(3)
                .withName("Kim")
                .withSurname("Butler")
                .withEmail("ante.iaculis@Donecnibhenim.ca")
                .withPassword("451-2416")
                .withTelephone("1")
                .withRole(Role.CLIENT)
                .build();
        Optional<User> user = userCrudDao.findUserByAccountId(3);
        assertEquals(expected, user.get());
    }

    @Test
    public void userCrudDaoFindByIdShouldThrowIllegalArgumentExceptionIfParameterIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        userCrudDao.findById(null);
    }

    @Test
    public void userCrudDaoFindByEmailShouldThrowIllegalArgumentExceptionIfParameterIsNull(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Passed value should not be null");
        userCrudDao.findByEmail(null);
    }

    @AfterClass
    public static void destroy() throws SQLException {
        connection.getConnection().close();
    }
}
