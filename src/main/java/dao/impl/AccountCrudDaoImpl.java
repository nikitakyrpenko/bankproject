package dao.impl;

import dao.*;
import dao.exception.DataBaseSqlRuntimeException;
import dao.util.ConnectorDB;
import dao.util.FetcherManager;
import dao.util.QueryManager;
import dao.util.enums.AccountQuery;
import domain.Account;
import domain.CreditAccount;
import domain.DepositAccount;
import domain.User;
import domain.enums.AccountType;
import org.apache.log4j.Logger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AccountCrudDaoImpl extends AbstractCrudDaoImp<Account> implements AccountDao {

    private static Logger LOGGER = Logger.getLogger(AccountCrudDaoImpl.class);

    private static Map<Enum, String> ACCOUNT_TO_QUERY = QueryManager.getInstance().getQueryMap(Account.class).get();
    private static final FetcherManager fetcherManager = FetcherManager.getInstance();
    private static final String FIND_BY_ID_QUERY;
    private static final String FIND_ALL_ACCOUNTS_QUERY;
    private static final String FIND_ALL_ACCOUNTS_PAGEABLE;
    private static final String INSERT_DEPOSIT_ACCOUNT_QUERY;
    private static final String INSERT_CREDIT_ACCOUNT_QUERY;
    private static final String UPDATE_DEPOSIT_QUERY;
    private static final String UPDATE_CREDIT_QUERY ;
    private static final String FIND_ALL_ACCOUNT_BY_USER;


    static {
        FIND_BY_ID_QUERY             = ACCOUNT_TO_QUERY.get(AccountQuery.FIND_BY_ID);
        FIND_ALL_ACCOUNTS_QUERY      = ACCOUNT_TO_QUERY.get(AccountQuery.FIND_ALL);
        FIND_ALL_ACCOUNTS_PAGEABLE   = ACCOUNT_TO_QUERY.get(AccountQuery.FIND_ALL_PAGEABLE);
        INSERT_DEPOSIT_ACCOUNT_QUERY = ACCOUNT_TO_QUERY.get(AccountQuery.INSERT_DEPOSIT_ACCOUNT);
        INSERT_CREDIT_ACCOUNT_QUERY  = ACCOUNT_TO_QUERY.get(AccountQuery.INSERT_CREDIT_ACCOUNT);
        UPDATE_DEPOSIT_QUERY         = ACCOUNT_TO_QUERY.get(AccountQuery.UPDATE_DEPOSIT_ACCOUNT);
        UPDATE_CREDIT_QUERY          = ACCOUNT_TO_QUERY.get(AccountQuery.UPDATE_CREDIT_ACCOUNT);
        FIND_ALL_ACCOUNT_BY_USER     = ACCOUNT_TO_QUERY.get(AccountQuery.FIND_ALL_BY_USER_ID);
    }

    public AccountCrudDaoImpl(ConnectorDB connectorDB) {
        super(connectorDB, FIND_BY_ID_QUERY, FIND_ALL_ACCOUNTS_QUERY, FIND_ALL_ACCOUNTS_PAGEABLE);
    }

    @Override
    protected Account mapResultSetToEntity(ResultSet resultSet) {
        User holder     = fetcherManager.fetchUser(resultSet, getUserColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        Account account = fetcherManager.fetchAccount(resultSet, getAccountColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        account.setHolder(holder);
        return account;
    }

    @Override
    public List<Account> findAllAccountsByUserId(Integer id) {
        return findAllByParams(id,FIND_ALL_ACCOUNT_BY_USER,INT_PARAM_SETTER);
    }

    @Override
    public void save(Account entity) {
        if (entity.getAccountType() == AccountType.DEPOSIT) {
            try (final PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_DEPOSIT_ACCOUNT_QUERY)) {
                DepositAccount depositAccount = (DepositAccount) entity;
                statement.setDate(1, new Date(depositAccount.getExpirationDate().getTime()));
                statement.setDouble(2, depositAccount.getBalance());
                statement.setDouble(3, depositAccount.getDepositRate());
                statement.setInt(4, depositAccount.getHolder().getId());
                statement.setInt(5, AccountType.DEPOSIT.ordinal());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            try (final PreparedStatement statement = connector.getConnection().prepareStatement(INSERT_CREDIT_ACCOUNT_QUERY)) {
                CreditAccount creditAccount = (CreditAccount) entity;
                statement.setDate(1, new Date(creditAccount.getExpirationDate().getTime()));
                statement.setDouble(2, creditAccount.getBalance());
                statement.setDouble(3, creditAccount.getLimit());
                statement.setDouble(4, creditAccount.getRate());
                statement.setDouble(5, creditAccount.getCharge());
                statement.setDouble(6, creditAccount.getLiability());
                statement.setInt(7, creditAccount.getHolder().getId());
                statement.setInt(8, AccountType.CREDIT.ordinal());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(Account entity) {
        if (entity.getAccountType() == AccountType.DEPOSIT) {
            DepositAccount depositAccount = (DepositAccount) entity;
            try (final PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_DEPOSIT_QUERY)) {
                statement.setDate(1, new Date(depositAccount.getExpirationDate().getTime()));
                statement.setDouble(2, depositAccount.getBalance());
                statement.setDouble(3, depositAccount.getDepositRate());
                statement.setInt(4, entity.getHolder().getId());
                statement.executeUpdate();
            } catch (SQLException  e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            CreditAccount creditAccount = (CreditAccount) entity;
            try (final PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_CREDIT_QUERY)) {
                statement.setDate(1, new Date(creditAccount.getExpirationDate().getTime()));
                statement.setDouble(2, creditAccount.getBalance());
                statement.setDouble(3, creditAccount.getLimit());
                statement.setDouble(4, creditAccount.getRate());
                statement.setDouble(5, creditAccount.getCharge());
                statement.setDouble(6, creditAccount.getLiability());
                statement.setInt(7, creditAccount.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    private String[] getUserColumnLabels() {
        return new String[]{
                "fk_roles_users",
                "users.id",
                "email",
                "firstname",
                "surname",
                "passwords",
                "telephone"
        };
    }

    private String[] getAccountColumnLabels(){
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


}
