package mapper;

import domain.User;
import entity.UserEntity;
import entity.enums.Role;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;
import service.mapper.UserMapper;


public class UserMapperTest {

    public static UserMapper userMapper;
    public static Encryptor encryptor;

    @BeforeClass
    public static void init(){
        encryptor = new PBKDF2Encryptor();
        userMapper = new UserMapper(encryptor);
    }

    @Test
    public void userMapperMapUserToUserEntityShouldReturnCorrectValue(){
        User user = User.builder()
                .withId(1)
                .withName("user")
                .withSurname("surname")
                .withTelephone("380508321899")
                .withPassword("P@ssword97")
                .withEmail("email@gmail.com")
                .withRole(Role.CLIENT)
                .build();
        UserEntity expected = UserEntity.builder()
                .withId(1)
                .withName("user")
                .withSurname("surname")
                .withTelephone("380508321899")
                .withPassword(encryptor.hash("P@ssword97".toCharArray()))
                .withEmail("email@gmail.com")
                .withRole(Role.CLIENT)
                .build();

        Assert.assertEquals(expected, userMapper.mapUserEntityToUser(user));
        //userMapper.
    }
}
