package service.validator;
import domain.User;
import entity.UserEntity;

import java.util.function.Function;
import java.util.regex.Pattern;

public class UserValidator implements Validator<UserEntity> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%/]).{6,100})");
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile("([+]*38[(]?[0-9]{1,4}[)]?[-\\s./0-9]*)");

    @Override
    public void validate(UserEntity user) {
        if (user == null) {
            throw new ValidateException("User is null");
        }
        validateEmail(user);
        validatePassword(user);
        validateTelephone(user);
    }

    private static void validateEmail(UserEntity user){
        validateString(EMAIL_PATTERN, user, UserEntity::getEmail, "Email do not match the pattern");
    }

    private static void validatePassword(UserEntity user){
        validateString(PASSWORD_PATTERN, user, UserEntity::getPassword, "Password do not match the pattern");
    }

    private static void validateTelephone(UserEntity user){
        validateString(TELEPHONE_PATTERN, user, UserEntity::getTelephone, "Telephone do not match the pattern");
    }

    private static void validateString(Pattern pattern, UserEntity user, Function<UserEntity, String> function,
                                       String exceptionMessage) {
        if (!pattern.matcher(function.apply(user)).matches()) {
            throw new ValidateException(exceptionMessage);
        }
    }


}
