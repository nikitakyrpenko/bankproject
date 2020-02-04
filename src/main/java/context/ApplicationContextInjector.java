package context;

import dao.UserDao;
import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;
import service.UserService;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;
import service.validator.UserValidator;
import service.validator.Validator;

public class ApplicationContextInjector {
    private static final ConnectorDB DB_CONNECTOR = new ConnectorDB("sqlconnection");
    private static final UserDao USER_DAO = new UserCrudDaoImpl(DB_CONNECTOR);
    private static final Encryptor PASSWORD_ENCODER = new PBKDF2Encryptor();
    //private static final UserMapper USER_MAPPER = new UserMapper(PASSWORD_ENCODER);
    private static final Validator<User> USER_VALIDATOR = new UserValidator();
    //private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR);

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

}
