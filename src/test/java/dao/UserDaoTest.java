package dao;

import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;
import domain.enums.Role;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


public class UserDaoTest {
    private static ConnectorDB connection;
    private static UserCrudDaoImpl userCrudDao;

    private static final String SCHEMA =
            "DROP TABLE IF EXISTS users; DROP TABLE IF EXISTS roles;";

    private static final String SCHEMA2 =
            "DROP TABLE IF EXISTS users; DROP TABLE IF EXISTS roles; " +
                    " CREATE TABLE IF NOT EXISTS roles (" +
                    "  role_id int NOT NULL AUTO_INCREMENT," +
                    "  role_description varchar(255) NOT NULL," +
                    "  PRIMARY KEY (role_id)," +
                    "  UNIQUE KEY role_d (role_id)" +
                    "); INSERT INTO roles(role_description) VALUES ('USER'),('ADMIN'); "+

            "CREATE TABLE IF NOT EXISTS users(" +
            "id int NOT NULL AUTO_INCREMENT," +
            "firstname varchar(15) NOT NULL," +
            "surname varchar(15) NOT NULL," +
            "email varchar(50) NOT NULL," +
            "passwords varchar(100) NOT NULL," +
            "telephone varchar(15) NOT NULL," +
            "fk_roles_users int NOT NULL," +
            " PRIMARY KEY (id)," +
            " CONSTRAINT id" +
            " UNIQUE (id)," +
            " CONSTRAINT email" +
            " UNIQUE (email)," +
            " FOREIGN KEY (fk_roles_users)" +
            " REFERENCES roles (role_id));" +
                    " INSERT INTO users VALUES " +
                    "(1,'Freya','Rodriguez','dolor.Donec@etmagnaPraesent.net','1','715-0987',1)," +
                    "(2,'Fleur','Morgan','Vivamus.rhoncus.Donec@lacusEtiam.net','1','612-4806',1)," +
                    "(3,'Kim','Butler','ante.iaculis@Donecnibhenim.ca','451-2416','1',1)," +
                    "(4,'Amaya','Davis','Vivamus.euismod.urna@ligulaAeneangravida.edu','1','338-6234',1);";



    @BeforeClass
    public static void init() throws Exception{
        connection = new ConnectorDB("h2connection");
        userCrudDao = new UserCrudDaoImpl(connection);
        Statement statement = connection.getConnection().createStatement();
        statement.executeUpdate(SCHEMA2);
    }

    @Test
    public void userCrudDaoFindAllShouldReturn4IfTableContains4Records(){
        Assert.assertEquals(userCrudDao.findAll().size(),4);
    }

    @Test
    public void userCrudDaoFindByIdShouldReturnCorrectRecordIfRecordExistInTable(){
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
        Assert.assertEquals(expected,actual.get());
    }


    @AfterClass
    public static void destroy() throws SQLException {
        connection.getConnection().close();
    }
}
