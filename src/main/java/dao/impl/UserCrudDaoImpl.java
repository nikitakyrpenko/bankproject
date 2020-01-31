package dao.impl;

import dao.*;
import dao.exception.DataBaseSqlRuntimeException;
import dao.util.ConnectorDB;
import dao.util.FetcherManager;
import dao.util.QueryManager;
import dao.util.enums.UserQuery;
import domain.User;
import domain.enums.Role;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class UserCrudDaoImpl extends AbstractCrudDaoImp<User> implements UserDao {
    private static Logger LOGGER = Logger.getLogger(UserCrudDaoImpl.class);

    private FetcherManager fetcherManager = FetcherManager.getInstance();
    private static Map<Enum, String> userToQuery = QueryManager.getInstance().getQueryMap(User.class).get();

    private static final String FIND_BY_ID_QUERY;
    private static final String FIND_BY_EMAIL_QUERY;
    private static final String INSERT_USER_QUERY;
    private static final String UPDATE_USER_QUERY;
    private static final String FIND_ALL_USERS_QUERY;
    private static final String FIND_ALL_USERS_PAGEABLE;
    private static final String FIND_USER_BY_ACCOUNT;

    static {
        FIND_BY_ID_QUERY        = userToQuery.get(UserQuery.FIND_BY_ID);
        FIND_BY_EMAIL_QUERY     = userToQuery.get(UserQuery.FIND_BY_EMAIL);
        FIND_ALL_USERS_PAGEABLE = userToQuery.get(UserQuery.FIND_ALL_PAGEABLE);
        FIND_ALL_USERS_QUERY    = userToQuery.get(UserQuery.FIND_ALL);
        FIND_USER_BY_ACCOUNT    = userToQuery.get(UserQuery.FIND_BY_ACCOUNT);
        INSERT_USER_QUERY       = userToQuery.get(UserQuery.INSERT_USER);
        UPDATE_USER_QUERY       = userToQuery.get(UserQuery.UPDATE_USER);
    }

    public UserCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_USERS_QUERY, FIND_ALL_USERS_PAGEABLE);
    }

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) {
        return fetcherManager.fetchUser(resultSet, getUserColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);
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
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
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
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String[] getUserColumnLabels() {
        return new String[]{
                "fk_roles_users",
                "id",
                "email",
                "firstname",
                "surname",
                "passwords",
                "telephone"
        };
    }
}
