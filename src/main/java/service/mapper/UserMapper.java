package service.mapper;

import domain.User;
import entity.UserEntity;
import service.encryptor.Encryptor;

public class UserMapper {

    //TODO ENCTYPTOR SHOULD NOT BE IN MAPPER
    private final Encryptor encryptor;

    public UserMapper(Encryptor encryptor){
        this.encryptor = encryptor;
    }


    public User mapUserToUserEntity(UserEntity userEntity){
        return User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withTelephone(userEntity.getTelephone())
                .withRole(userEntity.getRole())
                .build();
    }

    public UserEntity mapUserEntityToUser(User user){
        return UserEntity.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(encryptor.hash(user.getPassword().toCharArray()))
                .withTelephone(user.getTelephone())
                .withRole(user.getRole())
                .build();
    }

}
