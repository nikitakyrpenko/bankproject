package dao.impl;

import dao.*;
import dao.util.ConnectorDB;
import domain.User;
import domain.enums.Role;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserCrudDaoImpl extends AbstractCrudDaoImp<User> implements UserDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (`firstname`,`surname`,`email`,`passwords`,`telephone`,`fk_roles_users`) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET firstname = ?, surname = ?, email = ?, passwords = ?,telephone= ? WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_USERS_PAGEABLE = "SELECT * FROM users LIMIT ?, ?";
    private static final String FIND_USER_BY_ACCOUNT =
            "SELECT * FROM bank_database.users\n" +
                    "WHERE bank_database.users.id IN \n" +
                    "(SELECT bank_database.accounts.id FROM bank_database.accounts WHERE accounts.fk_users_accounts = ?)";

    public UserCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_USERS_QUERY, FIND_ALL_USERS_PAGEABLE, DELETE_BY_ID_QUERY);
    }

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Role role = resultSet.getInt("fk_roles_users") == 1 ? Role.CLIENT : Role.ADMIN;

        return User.builder()
                .withId(resultSet.getInt("id"))
                .withEmail(resultSet.getString("email"))
                .withName(resultSet.getString("firstname"))
                .withSurname(resultSet.getString("surname"))
                .withPassword(resultSet.getString("passwords"))
                .withTelephone(resultSet.getString("telephone"))
                .withRole(role)
                .build();

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    public Optional<User> findUserByAccountId(Integer id) {
        return findByParam(id, FIND_USER_BY_ACCOUNT, INT_PARAM_SETTER);
    }

    @Override
    public void save(User entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getTelephone());
            statement.setInt(6, entity.getRole() == Role.CLIENT ? 1 : 2);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_USER_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getTelephone());
            statement.setInt(6, entity.getId());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
