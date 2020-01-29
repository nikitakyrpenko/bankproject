package dao;

import domain.Operation;

import java.util.List;

public interface OperationDao extends CrudPageableDao<Operation> {

    List<Operation> findAllOperationByAccount(Integer id);

}
