package validator;


import domain.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.validator.UserValidator;
import service.validator.ValidateException;
import service.validator.Validator;

public class UserValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static Validator<User> userValidator;

    @BeforeClass
    public static void init(){ userValidator = new UserValidator();}

    @Test
    public void userValidatorValidateShouldThrowValidateExceptionIfEmailNotMatchPattern(){
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Email do not match the pattern");
        User user = User.builder()
                .withEmail("@email.com")
                .withPassword("123")
                .build();
        userValidator.validate(user);
    }

    @Test
    public void userValidatorValidateShouldThrowValidateExceptionIfEmailMatchesButPasswordNot(){
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Password do not match the pattern");
        User user = User.builder()
                .withEmail("mickaborscha@gmail.com")
                .withPassword("123")
                .build();
        userValidator.validate(user);
    }

    @Test
    public void userValidatorValidateShouldThrowValidateExceptionIfEmailAndPasswordMatchesButTelephoneNot(){
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Telephone do not match the pattern");
        User user = User.builder()
                .withEmail("mickaborscha@gmail.com")
                .withPassword("Mut@bor70")
                .withTelephone("123")
                .build();
        userValidator.validate(user);
    }

    @Test
    public void userValidatorValidateShouldPassIfUserFullyCorrect(){
        User user = User.builder()
                .withEmail("mickaborscha@gmail.com")
                .withPassword("H@kkMeIfUCan97")
                .withTelephone("380508321899")
                .build();
        userValidator.validate(user);
    }

    @AfterClass
    public static void destroy(){userValidator = null;}
}
