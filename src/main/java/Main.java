import dao.UserDao;
import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;
import service.mapper.UserMapper;

public class Main {

    public static void main(String[] args) {
        /*Encryptor encryptor = new PBKDF2Encryptor();
        UserDao userDao = new UserCrudDaoImpl(new ConnectorDB("sqlconnection"));
        userDao.findByEmail("admin@admin.com")
                .map(new UserMapper()::mapUserToUserEntity)
                .filter(user -> encryptor.checkPassword(password.toCharArray(), user.getPassword()));*/
    }
}
