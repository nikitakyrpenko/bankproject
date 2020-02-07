package dao;

import dao.util.Page;
import dao.util.Pageable;
import entity.AccountEntity;

import java.util.List;


public interface AccountDao extends CrudPageableDao<AccountEntity>{

    List<AccountEntity> findAllAccountsByUserId(Integer id);

    //Pageable<List<AccountEntity>> findAllAccountsByUserIdPageable(Integer id, Page page);

}
