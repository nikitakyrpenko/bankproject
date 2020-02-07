package dao.impl;

import dao.AbstractCrudDaoImp;
import dao.ChargeDao;
import dao.exception.DataBaseSqlRuntimeException;
import dao.util.ConnectorDB;
import dao.util.FetcherManager;
import dao.util.QueryManager;
import dao.util.enums.ChargeQuery;
import entity.AccountEntity;
import entity.ChargeEntity;
import entity.UserEntity;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChargeCrudDaoImpl extends AbstractCrudDaoImp<ChargeEntity> implements ChargeDao {
    private static Logger LOGGER = Logger.getLogger(ChargeCrudDaoImpl.class);

    private FetcherManager fetcherManager = FetcherManager.getInstance();
    private static Map<Enum, String> chargeToQuery = QueryManager
            .getInstance()
            .getQueryMap(ChargeEntity.class)
            .get();

    private static final String FIND_BY_ID_QUERY;
    private static final String FIND_ALL_QUERY;
    private static final String FIND_ALL_PAGEABLE;
    private static final String INSERT_QUERY;
    private static final String UPDATE_QUERY;
    private static final String FIND_ALL_CHARGES_BY_ACCOUNT_ID;

    static {
        FIND_BY_ID_QUERY  = chargeToQuery.get(ChargeQuery.FIND_BY_ID);
        FIND_ALL_QUERY    = chargeToQuery.get(ChargeQuery.FIND_ALL);
        FIND_ALL_PAGEABLE = chargeToQuery.get(ChargeQuery.FIND_ALL_PAGEABLE);
        INSERT_QUERY      = chargeToQuery.get(ChargeQuery.INSERT_CHARGE);
        UPDATE_QUERY      = chargeToQuery.get(ChargeQuery.UPDATE_CHARGE);
        FIND_ALL_CHARGES_BY_ACCOUNT_ID = chargeToQuery.get(ChargeQuery.FIND_ALL_BY_ACCOUNT);
    }

    public ChargeCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGEABLE);
    }

    @Override
    protected ChargeEntity mapResultSetToEntity(ResultSet resultSet){

        UserEntity userEntity = fetcherManager.fetchUser(resultSet, getUserColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        AccountEntity accountEntity = fetcherManager.fetchAccount(resultSet, getAccountColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        ChargeEntity chargeEntity = fetcherManager.fetchCharge(resultSet, getChargeColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        accountEntity.setHolder(userEntity);
        chargeEntity.setAccountEntity(accountEntity);
        return chargeEntity;
    }

    @Override
    public List<ChargeEntity> findAllChargesByAccountId(Integer id) {
        return findAllByParams(id, FIND_ALL_CHARGES_BY_ACCOUNT_ID, INT_PARAM_SETTER);
    }

    @Override
    public void save(ChargeEntity entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_QUERY)) {
            statement.setDouble(1, entity.getCharge());
            statement.setInt(2, entity.getChargeType().ordinal());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ChargeEntity entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setDouble(1, entity.getCharge());
            statement.setInt(2, entity.getChargeType().ordinal());
            statement.setInt(3, entity.getAccountEntity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String[] getAccountColumnLabels() {
        return new String[]{
                "fk_accounts_type_accounts",
                "accounts.account_id",
                "expiration_date",
                "balance",
                "deposit_account_rate",
                "credit_limit",
                "credit_rate",
                "charge_per_month",
                "credit_liabilities",
        };
    }

    private String[] getChargeColumnLabels() {
        return new String[]{
                "charges.charge_id",
                "charge",
                "fk_charge_types_charge",
        };
    }
    private String[] getUserColumnLabels() {
        return new String[]{
                "fk_roles_users",
                "user_id",
                "email",
                "firstname",
                "surname",
                "passwords",
                "telephone"
        };
    }
}
