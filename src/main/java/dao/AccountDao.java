package dao;

import domain.Account;

import java.util.List;


public interface AccountDao extends CrudPageableDao<Account>{

    List<Account> findAllAccountsByUserId(Integer id);

}
