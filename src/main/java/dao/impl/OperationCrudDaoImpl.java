package dao.impl;

import dao.AbstractCrudDaoImp;
import dao.util.ConnectorDB;
import dao.OperationDao;
import domain.Operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperationCrudDaoImpl extends AbstractCrudDaoImp<Operation> implements OperationDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM operations WHERE id=?";
    private static final String FIND_ALL_OPERATION_QUERY = "SELECT * FROM operations";
    private static final String FIND_ALL_OPERATION_PAGEABLE = "SELECT * FROM operations LIMIT ?, ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM operations WHERE id=?";
    private static final String FIND_OPERATION_BY_ACCOUNT = "select *\n" +
            "from operations\n" +
            "where operations.id IN\n" +
            "(select fk_operations_operations_id\n" +
            "from accounts_operations where accounts_operations.fk_accounts_receiver = ? OR accounts_operations.fk_accounts_sender = ?);";

    public OperationCrudDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_OPERATION_QUERY, FIND_ALL_OPERATION_PAGEABLE, DELETE_BY_ID_QUERY);
    }

    @Override
    protected Operation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Operation.builder()
                .withId(resultSet.getInt("id"))
                .withPurpose(resultSet.getString("purpose"))
                .withTransfer(resultSet.getDouble("transfer"))
                .withDate(resultSet.getDate("operation_date"))
                .build();
    }

    @Override
    public List<Operation> findAllOperationByAccount(Integer id) {
        return findAllByParams(id, FIND_OPERATION_BY_ACCOUNT, BI_INT_PARAM_SETTER);
    }


    @Override
    public void save(Operation entity) {
        //TODO many to many insert
    }

    @Override
    public void update(Operation entity) {
        //TODO many to many update
    }
}
