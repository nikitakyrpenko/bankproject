package dao.impl;

import dao.*;
import dao.util.ConnectorDB;
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
import java.util.Optional;

public class AccountCrudDaoImpl extends AbstractCrudDaoImp<Account> implements AccountDao {

    private static org.apache.log4j.Logger log = Logger.getLogger(AccountCrudDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM accounts WHERE id=?";
    private static final String FIND_ALL_ACCOUNTS_QUERY = "SELECT * FROM accounts";
    private static final String FIND_ALL_ACCOUNTS_PAGEABLE = "SELECT * FROM accounts LIMIT ?, ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM accounts WHERE id=?";
    private static final String INSERT_DEPOSIT_ACCOUNT_QUERY =
            "INSERT INTO accounts (`expiration_date`,`balance`,`deposit_account_rate`,`fk_users_accounts`,`fk_accounts_type_accounts`) " +
                    "VALUES (?,?,?,?,?)";
    private static final String INSERT_CREDIT_ACCOUNT_QUERY =
            "INSERT INTO accounts (`expiration_date`,`balance`,`credit_limit`,`credit_rate`,`charge_per_month`,`credit_liabilities`,`fk_users_accounts`,`fk_accounts_type_accounts`) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_DEPOSIT_QUERY = "UPDATE accounts SET expiration_date = ?, balance = ?, deposit_account_rate = ? WHERE id = ?";
    private static final String UPDATE_CREDIT_QUERY = "UPDATE accounts SET expiration_date = ?, balance = ?, credit_limit = ?, credit_rate = ?, charge_per_month = ?, credit_liabilities= ? WHERE id = ?";
    private UserDao userDao;

    public AccountCrudDaoImpl(ConnectorDB connectorDB) {
        super(connectorDB, FIND_BY_ID_QUERY, FIND_ALL_ACCOUNTS_QUERY, FIND_ALL_ACCOUNTS_PAGEABLE, DELETE_BY_ID_QUERY);
        userDao = new UserCrudDaoImpl(connectorDB);
    }

    @Override
    protected Account mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Account account;
        Optional<User> holder = userDao.findById(resultSet.getInt("fk_users_accounts"));
        AccountType accountType = resultSet.getInt("fk_accounts_type_accounts") == 1 ? AccountType.DEPOSIT : AccountType.CREDIT;
        if (accountType == AccountType.DEPOSIT) {
            account = new DepositAccount.DepositAccountBuilder()
                    .withId(resultSet.getInt("id"))
                    .withHolder(holder.get())
                    .withDate(resultSet.getDate("expiration_date"))
                    .withDepositAmount(resultSet.getDouble("balance"))
                    .withDepositRate(resultSet.getDouble("deposit_account_rate"))
                    .withAccountType(accountType)
                    .build();
        } else {
            account = new CreditAccount.CreditAccountBuilder()
                    .withId(resultSet.getInt("id"))
                    .withHolder(holder.get())
                    .withDate(resultSet.getDate("expiration_date"))
                    .withBalance(resultSet.getDouble("balance"))
                    .withCreditLimit(resultSet.getDouble("credit_limit"))
                    .withCreditRate(resultSet.getDouble("credit_rate"))
                    .withCreditLiability(resultSet.getDouble("credit_liabilities"))
                    .withAccountType(accountType)
                    .build();
        }

        return account;
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
                statement.setInt(5, 1);
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                log.error(e);
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
                statement.setInt(8, 2);
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                log.error(e);
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
            } catch (SQLException | ClassNotFoundException e) {
                log.error(e);
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
            } catch (SQLException | ClassNotFoundException e) {
                log.error(e);
            }
        }
    }


}
