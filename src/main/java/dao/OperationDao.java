package dao;

import entity.OperationEntity;

import java.util.List;

public interface OperationDao extends CrudPageableDao<OperationEntity> {


    //TODO FINDALLBYACCOUNTPAGEABLE
    List<OperationEntity> findAllOperationByAccount(Integer id);

}
