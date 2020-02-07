package service.impl;

import dao.AccountDao;
import dao.UserDao;
import dao.util.Page;
import dao.util.Pageable;
import domain.Account;
import domain.User;
import entity.AccountEntity;
import entity.UserEntity;
import service.UserService;
import service.encryptor.Encryptor;
import service.mapper.AccountMapper;
import service.mapper.UserMapper;
import service.validator.DuplicateException;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    //TODO SWITCH USERENTITY TO USER


    private final UserDao userDao;
    private final AccountDao accountDao;
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final Validator<User> validator;
    private final Encryptor encryptor;

    public UserServiceImpl(UserDao userDao,
                           AccountDao accountDao,
                           UserMapper userMapper,
                           AccountMapper accountMapper,
                           Validator validator,
                           Encryptor encryptor){
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.validator = validator;
        this.encryptor = encryptor;
        this.accountDao = accountDao;
        this.accountMapper = accountMapper;
    }


    @Override
    public void register(User user){
        validator.validate(user);
        UserEntity userEntity = userMapper.mapUserEntityToUser(user);
        userDao.findByEmail(user.getEmail()).ifPresent( s -> {throw new DuplicateException("User already present");});
        userDao.save(userEntity);
    }

    @Override
    public User login(String email, String password) {
        return userDao.findByEmail(email)
                .map(userMapper::mapUserToUserEntity)
                .filter(user -> encryptor.checkPassword(password.toCharArray(), user.getPassword()))
                .orElseThrow(() -> {throw new NoSuchElementException("User not found");
                });
    }

    @Override
    public List<Account> findAllUsersAccounts(Integer id) {
        return accountDao.findAllAccountsByUserId(id)
                .stream()
                .map(accountMapper::mapAccountEntityToAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Pageable<List<Account>> findAllUsersAccountsPageable(Integer id, Page page) {
        List<Account> allAccountsByUserId = accountDao.findAllAccountsByUserId(id)
                .stream()
                .map(accountMapper::mapAccountEntityToAccount)
                .collect(Collectors.toList());

        return null;
    }
}
