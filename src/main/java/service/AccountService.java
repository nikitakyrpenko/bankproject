package service;

import dao.util.Page;
import dao.util.Pageable;
import domain.Account;


public interface AccountService {

    Pageable<Account> findAllAccountsByUserId(Integer id, Page page);
}
