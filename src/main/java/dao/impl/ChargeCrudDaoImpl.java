package dao.impl;

import dao.AbstractCrudDaoImp;
import dao.ChargeDao;
import dao.util.ConnectorDB;
import domain.Account;
import domain.Charge;
import domain.enums.ChargeType;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChargeCrudDaoImpl extends AbstractCrudDaoImp<Charge> implements ChargeDao {
    private static org.apache.log4j.Logger log = Logger.getLogger(ChargeCrudDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM charges WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM charges";
    private static final String FIND_ALL_PAGEABLE = "SELECT * FROM charges LIMIT ?, ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM charges WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO charges (`charge`,`fk_charge_types_charge`,`fk_account_charge`,) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE charges SET charge = ?, fk_charge_types_charge = ? WHERE id = ?";
    private static final String FIND_ALL_CHARGES_BY_ACCOUNT_ID = "select * from charges where fk_account_charge = ?;";

    private final AccountCrudDaoImpl accountCrudDao;

    public ChargeCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGEABLE, DELETE_BY_ID_QUERY);
        accountCrudDao = new AccountCrudDaoImpl(connector);
    }


    @Override
    protected Charge mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Double charge = resultSet.getDouble("charge");
        Optional<Account> account = accountCrudDao.findById(resultSet.getInt("fk_account_charge"));
        ChargeType chargeType = resultSet.getInt("fk_charge_types_charge") == 1 ? ChargeType.DEPOSIT_ARRIVAL : ChargeType.CREDIT_ARRIVAL;
        return new Charge(id, charge, chargeType, account.get());
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
}
