package context;

import command.Command;
import command.impl.LanguageCommand;
import command.impl.LoginCommand;
import command.impl.RegisterCommand;
import dao.AccountDao;
import dao.UserDao;
import dao.impl.AccountCrudDaoImpl;
import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;
import service.AccountService;
import service.UserService;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;
import service.impl.AccountServiceImpl;
import service.impl.UserServiceImpl;
import service.mapper.AccountMapper;
import service.mapper.UserMapper;
import service.validator.UserValidator;
import service.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static final ConnectorDB DB_CONNECTOR = new ConnectorDB("sqlconnection");
    private static final UserDao USER_DAO = new UserCrudDaoImpl(DB_CONNECTOR);
    private static final AccountDao ACCOUNT_DAO = new AccountCrudDaoImpl(DB_CONNECTOR);
    private static final Encryptor PASSWORD_ENCODER = new PBKDF2Encryptor();
    private static final UserMapper USER_MAPPER = new UserMapper(PASSWORD_ENCODER);
    private static final AccountMapper ACCOUNT_MAPPER = new AccountMapper();
    private static final Validator<User> USER_VALIDATOR = new UserValidator();
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO,ACCOUNT_DAO, USER_MAPPER,ACCOUNT_MAPPER, USER_VALIDATOR, PASSWORD_ENCODER);
    private static final AccountService ACCOUNT_SERVICE = new AccountServiceImpl(ACCOUNT_DAO,ACCOUNT_MAPPER);
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);
    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE,ACCOUNT_SERVICE);
    private static final Command LANGUAGE_COMMAND = new LanguageCommand();
    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();



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

    public  UserService getUserService() {
        return USER_SERVICE;
    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("language", LANGUAGE_COMMAND);
        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }
}
