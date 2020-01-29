package dao;

import domain.User;

import java.util.Optional;

public interface UserDao extends CrudPageableDao<User> {

    Optional<User> findByEmail(String email);

    Optional<User> findUserByAccountId(Integer id);
}
