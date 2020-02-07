package encryptor;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class EncryptorTest {

    public static Encryptor encryptor;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Parameterized.Parameter
    public String password;

    @Parameterized.Parameter(1)
    public String token;

    @Parameterized.Parameter(2)
    public String wrongToken;

    @Parameterized.Parameter(3)
    public String exceptionToken;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(
                new Object[][]{
                        {"Mut@abor70",
                                "$31$16$tIHhKhdcZVLGFEp4PVmU_lkBimv7jrn2WBJO3MllGAw",
                                "$31$16$tIHhKhdcZVLGDep4PVmU_lkBimv7opq2WBJO3MllGAw",
                                "$31$16$tIHhKhxdcZVLGDep4PVmU_lkBim4v7opq2WBJO3MllG1Aw"},
                        {"~VeryH@rdPassWORDToDecrypt~"
                                ,"$31$16$wpNo3bpelVnIGDEjMczeycVSyHHnewY57GSvs2BiYoU",
                                 "$31$16$wpNo3omegVnIGDEjMczeycVSyHHnewY57GSvs2BiYoU",
                                 "$31$16$wZpNo3omegVnIGDEjMczeycV1SyHHnewY57GSvs2BiYoU"},
                        {"H@kkMeIfUCan",
                                "$31$16$VqDOlxM_ycW8-4YOwXPQRup_ec8vqZeEamSndrDgRxY",
                                "$31$16$VqDOlxM_dcW8-4UOwXPQRup_ec8vqZeEamSndrDgRzY",
                                "$41$16$VqDOlxM_dcW8-4UOwXxPQRup_ec8vqZeEamSndrDgRz1Y"},

                }
        );
    }

    @BeforeClass
    public static void init(){encryptor = new PBKDF2Encryptor();}

    @Test
    public void encryptorShouldReturnTrueIfPasswordMatchToken(){

        Assert.assertTrue(encryptor.checkPassword(password.toCharArray(),token));
    }

    @Test
    public void encryptorShouldReturnFalseIfPasswordDoNotMatchToken(){
        Assert.assertFalse(encryptor.checkPassword(password.toCharArray(), wrongToken));
    }

    @Test
    public void encryptorShouldThrowIllegalArgumentExceptionIfTokenIsInvalid(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid token format");
        encryptor.checkPassword(password.toCharArray(), exceptionToken);
    }

    @AfterClass
    public static void destroy(){encryptor = null;}

}
