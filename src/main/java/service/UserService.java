package service;

import dao.util.Page;
import dao.util.Pageable;
import domain.Account;
import domain.User;
import entity.AccountEntity;
import entity.UserEntity;
import service.validator.DuplicateException;
import service.validator.ValidateException;


import java.util.List;
import java.util.NoSuchElementException;


public interface UserService {

    void register(User user) throws DuplicateException, ValidateException;

    User login(String email, String password) throws NoSuchElementException;

    List<Account> findAllUsersAccounts(Integer id);

    Pageable<List<Account>> findAllUsersAccountsPageable(Integer id, Page page);

}
