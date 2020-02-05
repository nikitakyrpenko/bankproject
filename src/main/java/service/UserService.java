package service;

import domain.User;
import entity.UserEntity;
import service.validator.DuplicateException;
import service.validator.ValidateException;

import java.util.List;
import java.util.NoSuchElementException;


public interface UserService {

    void register(UserEntity user) throws DuplicateException, ValidateException;

    UserEntity login(String email, String password) throws NoSuchElementException;

    List<User> findAll();

}
