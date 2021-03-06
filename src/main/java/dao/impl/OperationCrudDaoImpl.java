package dao.impl;

import dao.AbstractCrudDaoImp;
import dao.exception.DataBaseSqlRuntimeException;
import dao.util.ConnectorDB;
import dao.OperationDao;
import dao.util.FetcherManager;
import dao.util.QueryManager;
import dao.util.enums.OperationQuery;
import entity.AccountEntity;
import entity.OperationEntity;
import entity.UserEntity;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class OperationCrudDaoImpl extends AbstractCrudDaoImp<OperationEntity> implements OperationDao {

    private static final FetcherManager fetcherManager = FetcherManager.getInstance();
    private static final String FIND_BY_ID_QUERY ;
    private static final String FIND_ALL_OPERATION_QUERY ;
    private static final String FIND_ALL_OPERATION_PAGEABLE;
    private static final String FIND_OPERATION_BY_ACCOUNT;
    private static final Map<Enum, String> operationToQuery = QueryManager.getInstance().getQueryMap(OperationEntity.class).get();

    static {
        FIND_BY_ID_QUERY            = operationToQuery.get(OperationQuery.FIND_ALL_BY_ID);
        FIND_ALL_OPERATION_QUERY    = operationToQuery.get(OperationQuery.FIND_ALL);
        FIND_ALL_OPERATION_PAGEABLE = operationToQuery.get(OperationQuery.FIND_ALL_PAGEABLE);
        FIND_OPERATION_BY_ACCOUNT   = operationToQuery.get(OperationQuery.FIND_BY_ACCOUNT);
    }

    public OperationCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_OPERATION_QUERY, FIND_ALL_OPERATION_PAGEABLE);
    }

    @Override
    protected OperationEntity mapResultSetToEntity(ResultSet resultSet) {

        UserEntity receiverOfOperation = fetcherManager.fetchUser(resultSet,getOperationReceiverColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        UserEntity senderOfOperation   = fetcherManager.fetchUser(resultSet, getOperationSenderColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        AccountEntity receiverAccountEntity = fetcherManager.fetchAccount(resultSet, getOperationReceiverAccountColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        AccountEntity senderAccountEntity = fetcherManager.fetchAccount(resultSet, getOperationSenderAccountColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        OperationEntity operationEntity = fetcherManager.fetchOperation(resultSet, getOperationColumnLabels())
                .orElseThrow(DataBaseSqlRuntimeException::new);

        receiverAccountEntity.setHolder(receiverOfOperation);
        senderAccountEntity.setHolder(senderOfOperation);
        operationEntity.setSenderOfTransaction(senderAccountEntity);
        operationEntity.setReceiverOfTransaction(receiverAccountEntity);

        return operationEntity;
    }

    @Override
    public List<OperationEntity> findAllOperationByAccount(Integer id) {
        return findAllByParams(id, FIND_OPERATION_BY_ACCOUNT, BI_INT_PARAM_SETTER);
    }


    @Override
    public void save(OperationEntity entity) {
        //TODO many to many insert
    }

    @Override
    public void update(OperationEntity entity) {
        //TODO many to many update
    }


  private String[] getOperationReceiverColumnLabels(){
        return new String[] {
              "receiver_user_role",
              "receiver_user_id",
              "receiver_user_email",
              "receiver_user_firstname",
              "receiver_user_surname",
              "receiver_user_password",
              "receiver_user_telephone"
      };
  }

  private String[] getOperationSenderColumnLabels(){
      return new String[] {
              "sender_user_role",
              "sender_user_id",
              "sender_user_email",
              "sender_user_firstname",
              "sender_user_surname",
              "sender_user_password",
              "sender_user_telephone"
      };
  }

  private String[] getOperationReceiverAccountColumnLabels(){
        return new String[]{
                "receiver_account_type",
                "receiver_id",
                "receiver_expiration_date",
                "receiver_balance",
                "receiver_deposit_account_rate",
                "receiver_credit_limit",
                "receiver_credit_rate",
                "receiver_charge_per_month",
                "receiver_credit_liabilities",
        };
  }
    private String[] getOperationSenderAccountColumnLabels(){
        return new String[]{
                "sender_account_type",
                "sender_id",
                "sender_expiration_date",
                "sender_balance",
                "sender_deposit_account_rate",
                "sender_credit_limit",
                "sender_credit_rate",
                "sender_charge_per_month",
                "sender_credit_liabilities",
        };
    }

    private String[] getOperationColumnLabels(){
        return new String[]{
                "operation_id",
                "operation_purpose",
                "operation_transfer",
                "operation_date"
        };
    }
}
