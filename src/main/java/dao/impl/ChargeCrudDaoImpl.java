package dao.impl;

import dao.AbstractCrudDaoImp;
import dao.ChargeDao;
import dao.exception.DataBaseSqlRuntimeException;
import dao.util.ConnectorDB;
import dao.util.FetcherManager;
import dao.util.QueryManager;
import dao.util.enums.ChargeQuery;
import domain.Account;
import domain.Charge;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChargeCrudDaoImpl extends AbstractCrudDaoImp<Charge> implements ChargeDao {
    private static org.apache.log4j.Logger log = Logger.getLogger(ChargeCrudDaoImpl.class);

    private FetcherManager fetcherManager = FetcherManager.getInstance();
    private static Map<Enum, String> chargeToQuery = QueryManager.getInstance().getQueryMap(Charge.class).get();

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
    protected Charge mapResultSetToEntity(ResultSet resultSet){

        Account account = fetcherManager.fetchAccount(resultSet, getAccountColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        Charge charge = fetcherManager.fetchCharge(resultSet, getChargeColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        charge.setAccount(account);
        return charge;
    }

    @Override
    public List<Charge> findAllChargesByAccountId(Integer id) {
        return findAllByParams(id, FIND_ALL_CHARGES_BY_ACCOUNT_ID, INT_PARAM_SETTER);
    }

    @Override
    public void save(Charge entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_QUERY)) {
            statement.setDouble(1, entity.getCharge());
            statement.setInt(2, entity.getChargeType().ordinal());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Charge entity) {
        try (final PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setDouble(1, entity.getCharge());
            statement.setInt(2, entity.getChargeType().ordinal());
            statement.setInt(3, entity.getAccount().getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
    }

    private String[] getAccountColumnLabels() {
        return new String[]{
                "fk_accounts_type_accounts",
                "accounts.id",
                "expiration_date",
                "balance",
                "deposit_account_rate",
                "credit_limit",
                "charge_per_month",
                "credit_liabilities",
        };
    }

    private String[] getChargeColumnLabels() {
        return new String[]{
                "charges.id",
                "charge",
                "fk_charge_types_charge",
        };
    }
}
