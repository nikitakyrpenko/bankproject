package service.mapper;

import domain.User;
import entity.UserEntity;
import service.encryptor.Encryptor;

public class UserMapper {

    private final Encryptor encryptor;

    public UserMapper(Encryptor encryptor){
        this.encryptor = encryptor;
    }


    public UserEntity mapUserToUserEntity(User user){
        return UserEntity.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withTelephone(user.getTelephone())
                .withRole(user.getRole())
                .build();
    }

    public User mapUserEntityToUser(UserEntity userEntity){
        return User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(encryptor.hash(userEntity.getPassword().toCharArray()))
                .withTelephone(userEntity.getTelephone())
                .withRole(userEntity.getRole())
                .build();
    }

}
