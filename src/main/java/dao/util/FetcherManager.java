package dao.util;

import domain.*;
import domain.enums.AccountType;
import domain.enums.ChargeType;
import domain.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FetcherManager {
    //private static Logger LOGGER = Logger.getLogger(FetcherManager.class);

    private static final FetcherManager INSTANCE = new FetcherManager();

    private FetcherManager() {
    }

    public static FetcherManager getInstance() {
        return INSTANCE;
    }

    public Optional<User> fetchUser(ResultSet resultSet, String[] columnLabels) {
        User userToReturn = null;
        try {
            Role role = resultSet.getInt(columnLabels[0]) == 1 ? Role.CLIENT : Role.ADMIN;
            userToReturn = User.builder()
                    .withId(resultSet.getInt(columnLabels[1]))
                    .withEmail(resultSet.getString(columnLabels[2]))
                    .withName(resultSet.getString(columnLabels[3]))
                    .withSurname(resultSet.getString(columnLabels[4]))
                    .withPassword(resultSet.getString(columnLabels[5]))
                    .withTelephone(resultSet.getString(columnLabels[6]))
                    .withRole(role)
                    .build();
        } catch (SQLException e) {
           // LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(userToReturn);
    }



    public Optional<Account> fetchAccount(ResultSet resultSet, String[] columnLabels) {
        Account accountToReturn = null;
        try {
            AccountType accountType = resultSet.getInt(columnLabels[0]) == 1 ? AccountType.DEPOSIT : AccountType.CREDIT;
            System.out.println(accountType);
            if (accountType == AccountType.DEPOSIT) {
                accountToReturn = new DepositAccount.DepositAccountBuilder()
                        .withId(resultSet.getInt(columnLabels[1]))
                        .withDate(resultSet.getDate(columnLabels[2]))
                        .withDepositAmount(resultSet.getDouble(columnLabels[3]))
                        .withDepositRate(resultSet.getDouble(columnLabels[4]))
                        .withAccountType(accountType)
                        .build();
            } else {
                accountToReturn = new CreditAccount.CreditAccountBuilder()
                        .withId(resultSet.getInt(columnLabels[1]))
                        .withDate(resultSet.getDate(columnLabels[2]))
                        .withBalance(resultSet.getDouble(columnLabels[3]))
                        .withCreditLimit(resultSet.getDouble(columnLabels[5]))
                        .withCreditRate(resultSet.getDouble(columnLabels[6]))
                        .withCreditLiability(resultSet.getDouble(columnLabels[7]))
                        .withAccountType(accountType)
                        .build();
            }
        } catch (SQLException e) {
            //LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(accountToReturn);
    }

    public Optional<Operation> fetchOperation(ResultSet resultSet,String[] columnLabels) {
        Operation operation = null;
        try {
            operation= Operation.builder()
                    .withId(resultSet.getInt(columnLabels[0]))
                    .withPurpose(resultSet.getString(columnLabels[1]))
                    .withTransfer(resultSet.getDouble(columnLabels[2]))
                    .withDate(resultSet.getDate(columnLabels[3]))
                    .build();
        } catch (SQLException e) {
            //LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(operation);
    }

    public Optional<Charge> fetchCharge(ResultSet resultSet, String[] columnLabels) {
        Charge chargeToReturn = null;
        try {
            Integer id = resultSet.getInt(columnLabels[0]);
            Double charge = resultSet.getDouble(columnLabels[1]);
            ChargeType chargeType = resultSet.getInt(columnLabels[2]) == 1 ? ChargeType.DEPOSIT_ARRIVAL : ChargeType.CREDIT_ARRIVAL;
            chargeToReturn = new Charge(id, charge, chargeType);
        } catch (SQLException e) {
            //LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(chargeToReturn);
    }
}
