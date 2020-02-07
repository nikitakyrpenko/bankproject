package dao.util;

import dao.util.enums.AccountQuery;
import dao.util.enums.ChargeQuery;
import dao.util.enums.OperationQuery;
import dao.util.enums.UserQuery;
import entity.AccountEntity;
import entity.ChargeEntity;
import entity.OperationEntity;
import entity.UserEntity;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class QueryManager {
    private static final QueryManager QUERY_MANAGER_INSTANCE = new QueryManager();
    private static final String OPERATION_FIND_ALL = "SELECT \n" +
            "operations.operation_id AS operation_id,\n" +
            "purpose AS operation_purpose,\n" +
            "transfer AS operation_transfer,\n" +
            " operation_date as operation_date," +
            "operation_with_receivers.account_id AS receiver_id,\n" +
            "operation_with_receivers.balance AS receiver_balance,\n" +
            "operation_with_receivers.expiration_date AS receiver_expiration_date,\n" +
            "operation_with_receivers.deposit_account_rate AS receiver_deposit_account_rate,\n" +
            "operation_with_receivers.credit_limit AS receiver_credit_limit,\n" +
            "operation_with_receivers.credit_rate AS receiver_credit_rate,\n" +
            "operation_with_receivers.charge_per_month AS receiver_charge_per_month,\n" +
            "operation_with_receivers.credit_liabilities AS receiver_credit_liabilities,\n" +
            "operation_with_receivers.fk_accounts_type_accounts AS receiver_account_type,\n" +
            "full_operation_with_accounts.account_id AS sender_id,\n" +
            "full_operation_with_accounts.balance AS sender_balance,\n" +
            "full_operation_with_accounts.expiration_date AS sender_expiration_date,\n" +
            "full_operation_with_accounts.deposit_account_rate AS sender_deposit_account_rate,\n" +
            "full_operation_with_accounts.credit_limit AS sender_credit_limit,\n" +
            "full_operation_with_accounts.credit_rate AS sender_credit_rate,\n" +
            "full_operation_with_accounts.charge_per_month AS sender_charge_per_month,\n" +
            "full_operation_with_accounts.credit_liabilities AS sender_credit_liabilities,\n" +
            "full_operation_with_accounts.fk_accounts_type_accounts AS sender_account_type,\n" +
            "full_operation_with_receivers_accounts.user_id AS receiver_user_id,\n" +
            "full_operation_with_receivers_accounts.firstname AS receiver_user_firstname,\n" +
            "full_operation_with_receivers_accounts.surname AS receiver_user_surname,\n" +
            "full_operation_with_receivers_accounts.email AS receiver_user_email,\n" +
            "full_operation_with_receivers_accounts.passwords AS receiver_user_password,\n" +
            "full_operation_with_receivers_accounts.telephone AS receiver_user_telephone,\n" +
            "full_operation_with_receivers_accounts.fk_roles_users AS receiver_user_role,\n" +
            "full_operation_with_senders_accounts.user_id AS sender_user_id,\n" +
            "full_operation_with_senders_accounts.firstname AS sender_user_firstname,\n" +
            "full_operation_with_senders_accounts.surname AS sender_user_surname,\n" +
            "full_operation_with_senders_accounts.email AS sender_user_email,\n" +
            "full_operation_with_senders_accounts.passwords AS sender_user_password,\n" +
            "full_operation_with_senders_accounts.telephone AS sender_user_telephone,\n" +
            "full_operation_with_senders_accounts.fk_roles_users AS sender_user_role\n" +
            "FROM\n" +
            "operations\n" +
            "INNER JOIN\n" +
            "accounts_operations accounts_with_operations ON (operations.operation_id = accounts_with_operations.fk_operations_operations_id)\n" +
            "INNER JOIN\n" +
            "accounts operation_with_receivers ON (accounts_with_operations.fk_accounts_receiver = operation_with_receivers.account_id)\n" +
            "INNER JOIN\n" +
            "accounts full_operation_with_accounts ON (fk_accounts_sender = full_operation_with_accounts.account_id)\n" +
            "INNER JOIN\n" +
            "users full_operation_with_receivers_accounts ON (accounts_with_operations.fk_accounts_receiver = full_operation_with_receivers_accounts.user_id)\n" +
            "INNER JOIN\n" +
            "users full_operation_with_senders_accounts ON (accounts_with_operations.fk_accounts_sender = full_operation_with_senders_accounts.user_id)";

    private static final String OPERATION_FIND_ALL_BY_ID = OPERATION_FIND_ALL + "WHERE operations.operation_id = ?";
    private static final String OPERATION_FIND_ALL_PAGEABLE = OPERATION_FIND_ALL + "LIMIT ?, ?";
    private static final String OPERATION_FIND_BY_ACCOUNT = OPERATION_FIND_ALL + "WHERE fk_accounts_receiver = ? OR fk_accounts_sender = ?; ";

    private static final String USER_FIND_ALL = "SELECT * FROM users";
    private static final String USER_FIND_BY_ID = "SELECT * FROM users WHERE users.user_id = ?";
    private static final String USER_FIND_ALL_PAGEABLE = "SELECT * FROM users LIMIT ?, ?";
    private static final String USER_FIND_BY_EMAIL = "SELECT * FROM users WHERE users.email=?";
    private static final String USER_FIND_BY_ACCOUNT = "select * from users WHERE users.user_id IN (SELECT accounts.fk_users_accounts FROM accounts WHERE accounts.account_id = ?)";
    private static final String USER_INSERT = "INSERT INTO users (`firstname`,`surname`,`email`,`passwords`,`telephone`,`fk_roles_users`) VALUES (?,?,?,?,?,?)";
    private static final String USER_UPDATE = "UPDATE users SET firstname = ?, surname = ?, email = ?, passwords = ?,telephone= ? WHERE user_id = ?";

    private static final String ACCOUNT_FIND_ALL = "select * from accounts INNER JOIN users ON accounts.fk_users_accounts = users.user_id";
    private static final String ACCOUNT_FIND_BY_ID = "select * from accounts INNER JOIN users ON accounts.fk_users_accounts = users.user_id WHERE accounts.account_id = ?";
    private static final String ACCOUNT_FIND_ALL_PAGEABLE = ACCOUNT_FIND_ALL + "LIMIT ?, ?";
    private static final String ACCOUNT_INSERT_DEPOSIT_ACCOUNT = "INSERT INTO accounts (`expiration_date`,`balance`,`deposit_account_rate`,`fk_users_accounts`,`fk_accounts_type_accounts`) VALUES (?,?,?,?,?)";
    private static final String ACCOUNT_INSERT_CREDIT_ACCOUNT = "INSERT INTO accounts (`expiration_date`,`balance`,`credit_limit`,`credit_rate`,`charge_per_month`,`credit_liabilities`,`fk_users_accounts`,`fk_accounts_type_accounts`) VALUES (?,?,?,?,?,?,?,?)";
    private static final String ACCOUNT_UPDATE_DEPOSIT_ACCOUNT = "UPDATE accounts SET expiration_date = ?, balance = ?, deposit_account_rate = ? WHERE account_id = ?";
    private static final String ACCOUNT_UPDATE_CREDIT_ACCOUNT = "UPDATE accounts SET expiration_date = ?, balance = ?, credit_limit = ?, credit_rate = ?, charge_per_month = ?, credit_liabilities= ? WHERE account_id = ?";
    private static final String ACCOUNT_FIND_BY_USER_ID = "select * from accounts INNER JOIN users ON accounts.fk_users_accounts = users.user_id WHERE users.user_id = ?";
    private static final String ACCOUNT_FIND_ALL_BU_USER_ID_PAGEABLE = ACCOUNT_FIND_BY_USER_ID + "LIMIT ?,?";

    private static final String CHARGE_FIND_ALL = "SELECT * " +
            "FROM" +
            "    charges" +
            "        INNER JOIN" +
            "    accounts ON charges.fk_account_charge = accounts.account_id" +
            "        INNER JOIN" +
            "    users ON fk_users_accounts = users.user_id ";

    private static final String CHARGE_FIND_ALL_BY_ACCOUNT = CHARGE_FIND_ALL +
            "WHERE" +
            "    accounts.account_id = ?";

    private static final String CHARGE_FIND_BY_ID = CHARGE_FIND_ALL +
            "WHERE" +
            "   charges.charge_id = ?";

    private static final String CHARGE_FIND_ALL_PAGEABLE = CHARGE_FIND_ALL +
            " LIMIT ?, ?";

    private static final String CHARGE_INSERT = "INSERT INTO charges (`charge`,`fk_charge_types_charge`,`fk_account_charge`,) VALUES (?,?,?)";
    private static final String CHARGE_UPDATE = "UPDATE charges SET charge = ?, fk_charge_types_charge = ? WHERE charge_id = ?";

    private QueryManager() {
    }

    public static QueryManager getInstance() {
        return QUERY_MANAGER_INSTANCE;
    }

    public Optional<Map<Enum, String>> getQueryMap(Class cls){
        Map<Enum, String> queryMap = null;
        if (OperationEntity.class == cls) {
            queryMap = new EnumMap(OperationQuery.class);
            queryMap.put(OperationQuery.FIND_ALL, OPERATION_FIND_ALL);
            queryMap.put(OperationQuery.FIND_ALL_BY_ID, OPERATION_FIND_ALL_BY_ID);
            queryMap.put(OperationQuery.FIND_ALL_PAGEABLE, OPERATION_FIND_ALL_PAGEABLE);
            queryMap.put(OperationQuery.FIND_BY_ACCOUNT, OPERATION_FIND_BY_ACCOUNT);
        }
        if (UserEntity.class == cls) {
            queryMap = new EnumMap(UserQuery.class);
            queryMap.put(UserQuery.FIND_ALL, USER_FIND_ALL);
            queryMap.put(UserQuery.FIND_BY_ID, USER_FIND_BY_ID);
            queryMap.put(UserQuery.FIND_ALL_PAGEABLE, USER_FIND_ALL_PAGEABLE);
            queryMap.put(UserQuery.FIND_BY_EMAIL, USER_FIND_BY_EMAIL);
            queryMap.put(UserQuery.FIND_BY_ACCOUNT, USER_FIND_BY_ACCOUNT);
            queryMap.put(UserQuery.INSERT_USER, USER_INSERT);
            queryMap.put(UserQuery.UPDATE_USER, USER_UPDATE);
        }
        if (cls == AccountEntity.class) {
            queryMap = new EnumMap(AccountQuery.class);
            queryMap.put(AccountQuery.FIND_ALL, ACCOUNT_FIND_ALL);
            queryMap.put(AccountQuery.FIND_BY_ID, ACCOUNT_FIND_BY_ID);
            queryMap.put(AccountQuery.FIND_ALL_PAGEABLE, ACCOUNT_FIND_ALL_PAGEABLE);
            queryMap.put(AccountQuery.FIND_ALL_BY_USER_ID, ACCOUNT_FIND_BY_USER_ID);
            queryMap.put(AccountQuery.INSERT_CREDIT_ACCOUNT, ACCOUNT_INSERT_CREDIT_ACCOUNT);
            queryMap.put(AccountQuery.INSERT_DEPOSIT_ACCOUNT, ACCOUNT_INSERT_DEPOSIT_ACCOUNT);
            queryMap.put(AccountQuery.UPDATE_CREDIT_ACCOUNT, ACCOUNT_UPDATE_CREDIT_ACCOUNT);
            queryMap.put(AccountQuery.UPDATE_DEPOSIT_ACCOUNT, ACCOUNT_UPDATE_DEPOSIT_ACCOUNT);
        }
        if (cls == ChargeEntity.class){
            queryMap = new EnumMap(ChargeQuery.class);
            queryMap.put(ChargeQuery.FIND_ALL, CHARGE_FIND_ALL);
            queryMap.put(ChargeQuery.FIND_BY_ID, CHARGE_FIND_BY_ID);
            queryMap.put(ChargeQuery.FIND_ALL_PAGEABLE, CHARGE_FIND_ALL_PAGEABLE);
            queryMap.put(ChargeQuery.INSERT_CHARGE, CHARGE_INSERT);
            queryMap.put(ChargeQuery.UPDATE_CHARGE, CHARGE_UPDATE);
            queryMap.put(ChargeQuery.FIND_ALL_BY_ACCOUNT, CHARGE_FIND_ALL_BY_ACCOUNT);
        }
        if (Objects.isNull(queryMap)){
            throw new RuntimeException("No such class");
        }
        return Optional.of(queryMap);
    }
}


