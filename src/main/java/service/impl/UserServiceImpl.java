package service.impl;

import dao.UserDao;
import domain.User;
import entity.UserEntity;
import service.UserService;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;
import service.mapper.UserMapper;
import service.validator.DuplicateException;
import service.validator.ValidateException;
import service.validator.Validator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    //TODO SWITCH USERENTITY TO USER

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final Validator<UserEntity> validator;
    private final Encryptor encryptor;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper, Validator validator, Encryptor encryptor){
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.validator = validator;
        this.encryptor = encryptor;
    }


    @Override
    public void register(UserEntity userEntity){
        validator.validate(userEntity);
        User user = userMapper.mapUserEntityToUser(userEntity);
        userDao.findByEmail(user.getEmail()).ifPresent( s -> {throw new DuplicateException("User already present");});
        userDao.save(user);
    }

    @Override
    public UserEntity login(String email, String password) {
        System.out.println("User service" + userDao.findByEmail(email));
        return userDao.findByEmail(email)
                .map(userMapper::mapUserToUserEntity)
                .filter(user -> encryptor.checkPassword(password.toCharArray(), user.getPassword()))
                .orElseThrow(() -> {throw new NoSuchElementException("User not found");
                });



    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
