package dao.util;

import entity.*;
import entity.enums.AccountType;
import entity.enums.ChargeType;
import entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FetcherManager {
    private static Logger LOGGER = Logger.getLogger(FetcherManager.class);

    private static final FetcherManager INSTANCE = new FetcherManager();

    private FetcherManager() {
    }

    public static FetcherManager getInstance() {
        return INSTANCE;
    }

    public Optional<UserEntity> fetchUser(ResultSet resultSet, String[] columnLabels) {
        UserEntity userEntityToReturn = null;
        try {
            Role role = resultSet.getInt(columnLabels[0]) == 1 ? Role.CLIENT : Role.ADMIN;
            userEntityToReturn = UserEntity.builder()
                    .withId(resultSet.getInt(columnLabels[1]))
                    .withEmail(resultSet.getString(columnLabels[2]))
                    .withName(resultSet.getString(columnLabels[3]))
                    .withSurname(resultSet.getString(columnLabels[4]))
                    .withPassword(resultSet.getString(columnLabels[5]))
                    .withTelephone(resultSet.getString(columnLabels[6]))
                    .withRole(role)
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(userEntityToReturn);
    }



    public Optional<AccountEntity> fetchAccount(ResultSet resultSet, String[] columnLabels) {
        AccountEntity accountEntityToReturn = null;
        try {
            AccountType accountType = resultSet.getInt(columnLabels[0]) == 1 ? AccountType.DEPOSIT : AccountType.CREDIT;
            System.out.println(accountType);
            if (accountType == AccountType.DEPOSIT) {
                accountEntityToReturn = new DepositAccountEntity.DepositAccountBuilder()
                        .withId(resultSet.getInt(columnLabels[1]))
                        .withDate(resultSet.getDate(columnLabels[2]))
                        .withDepositAmount(resultSet.getDouble(columnLabels[3]))
                        .withDepositRate(resultSet.getDouble(columnLabels[4]))
                        .withAccountType(accountType)
                        .build();
            } else {
                accountEntityToReturn = new CreditAccountEntity.CreditAccountBuilder()
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
            LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(accountEntityToReturn);
    }

    public Optional<OperationEntity> fetchOperation(ResultSet resultSet, String[] columnLabels) {
        OperationEntity operationEntity = null;
        try {
            operationEntity = OperationEntity.builder()
                    .withId(resultSet.getInt(columnLabels[0]))
                    .withPurpose(resultSet.getString(columnLabels[1]))
                    .withTransfer(resultSet.getDouble(columnLabels[2]))
                    .withDate(resultSet.getDate(columnLabels[3]))
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(operationEntity);
    }

    public Optional<ChargeEntity> fetchCharge(ResultSet resultSet, String[] columnLabels) {
        ChargeEntity chargeEntityToReturn = null;
        try {
            Integer id = resultSet.getInt(columnLabels[0]);
            Double charge = resultSet.getDouble(columnLabels[1]);
            ChargeType chargeType = resultSet.getInt(columnLabels[2]) == 1 ? ChargeType.DEPOSIT_ARRIVAL : ChargeType.CREDIT_ARRIVAL;
            chargeEntityToReturn = new ChargeEntity(id, charge, chargeType);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.ofNullable(chargeEntityToReturn);
    }
}
