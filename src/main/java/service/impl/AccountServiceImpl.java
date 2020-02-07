package service.impl;

import dao.AccountDao;
import dao.util.Page;
import dao.util.Pageable;
import domain.Account;
import service.AccountService;
import service.mapper.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountDao accountDao, AccountMapper accountMapper){
        this.accountDao = accountDao;
        this.accountMapper = accountMapper;
    }

    @Override
    public Pageable<Account> findAllAccountsByUserId(Integer id, Page page) {
        int pageOffset = page.getPageNumber() * page.getItemsPerPage();
        int pageSize = page.getItemsPerPage();

        List<Account> accounts = accountDao.findAllAccountsByUserId(id)
                .stream()
                .skip(pageOffset)
                .limit(pageSize)
                .map(accountMapper::mapAccountEntityToAccount)
                .collect(Collectors.toList());


        return new Pageable<>(accounts,page.getPageNumber(),page.getItemsPerPage(),10);
    }
}
