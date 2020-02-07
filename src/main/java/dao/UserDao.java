package dao;

import entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudPageableDao<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findUserByAccountId(Integer id);
}
